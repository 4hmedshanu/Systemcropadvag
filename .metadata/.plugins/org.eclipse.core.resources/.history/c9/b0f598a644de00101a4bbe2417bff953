// multilinguachabot.js

console.log("Chatbot Loaded");

// --- Configuration ---
// CORRECTED: Use port 8082 as seen in the Spring Boot logs.
const BACKEND_BASE_URL = 'http://localhost:8082'; 

// INPUTS & ELEMENTS
const input = document.querySelector("#dynamicpromt");
const sendBtn = document.querySelector("#submitpromt");
// Select the new chat history container for message insertion
const chatHistory = document.querySelector(".chat-history");

// --- Helper Functions (Unchanged) ---

// FUNCTION: Create User Message
function addUserMessage(text) {
    const row = document.createElement("div");
    row.classList.add("message-row", "user-row");

    const bubble = document.createElement("div");
    bubble.classList.add("msg-bubble", "user-bubble");
    bubble.innerText = text;

    // Create the user icon using the new .user-icon-small class
    const icon = document.createElement("div");
    icon.classList.add("user-icon-small");
    icon.innerHTML = `<i class="fas fa-user"></i>`;

    // User messages have the bubble on the left and the icon on the right
    row.appendChild(bubble);
    row.appendChild(icon);

    // Append to the chat history container
    chatHistory.appendChild(row);
    autoScroll();
    return bubble; // Return the bubble element for easy modification
}

// FUNCTION: Create AI Message (Modified to return the bubble)
function createAiMessage(initialText = "") {
    const row = document.createElement("div");
    row.classList.add("message-row", "bot-row");

    const icon = document.createElement("div");
    icon.classList.add("bot-icon-small");
    icon.innerHTML = `<i class="fas fa-robot"></i>`;

    const bubble = document.createElement("div");
    bubble.classList.add("msg-bubble", "bot-bubble");
    bubble.innerText = initialText;

    // Bot messages have the icon on the left and the bubble on the right
    row.appendChild(icon);
    row.appendChild(bubble);

    // Append to the chat history container
    chatHistory.appendChild(row);
    autoScroll();
    return bubble; // Return the bubble element for streaming updates
}

// FUNCTION: Auto-scroll (updated to scroll the chat history container)
function autoScroll() {
    chatHistory.scrollTop = chatHistory.scrollHeight;
}

// --- NEW STREAMING LOGIC ---

/**
 * Handles the actual API call and streaming of the response.
 * @param {string} question The user's query.
 */
async function sendStreamingRequest(question) {
    // 1. Create a message bubble immediately to show the AI is "typing"
    const aiBubble = createAiMessage("..."); // Placeholder text

    // 2. Construct the URL to your Spring Boot endpoint using the constant
    // Endpoint: http://localhost:8082/ai/ask?q=...
    const url = `${BACKEND_BASE_URL}/ai/ask?q=${encodeURIComponent(question)}`;

    try {

        const response = await fetch(url, {
        method: "GET",
        mode: "cors"
        });

        if (!response.body || !response.ok) {
            // Handle HTTP errors (e.g., 404, 500)
             let errorMessage = `Error: HTTP Status ${response.status}. Check backend endpoint '/ai/ask' or CORS configuration.`;
             if (response.status === 404) {
                 errorMessage = `Error: 404 Not Found. Check if the endpoint path '/ai/ask' is correct on the server.`;
             }
             aiBubble.innerText = errorMessage;
             return;
        }

        // 3. Get a reader to process the stream
        const reader = response.body.getReader();
        const decoder = new TextDecoder("utf-8");
        let fullResponse = '';
        
        // Clear the placeholder
        aiBubble.innerText = '';

        // 4. Read the stream loop
        while (true) {
            const { value, done } = await reader.read();
            if (done) break;

            const chunk = decoder.decode(value, { stream: true });
            
            // Append the new chunk to the bubble content
            fullResponse += chunk;
            aiBubble.innerText = fullResponse;

            autoScroll(); // Keep scrolling to the bottom with each new chunk
        }
        
    } catch (error) {
        console.error('Streaming request failed:', error);
        // CORRECTED: Reference the constant for the port in the error message
        aiBubble.innerText = `Error: Could not connect to the backend (${BACKEND_BASE_URL}). Check if the server is running and CORS is configured.`;
    }
}


// --- Event Handlers (Unchanged) ---

// HANDLE SEND BUTTON CLICK
sendBtn.addEventListener("click", () => {
    const text = input.value.trim();
    if (!text) return;

    addUserMessage(text); 
    input.value = ""; 
    
    // START the streaming request
    sendStreamingRequest(text);
});

// ENTER KEY SUPPORT
input.addEventListener("keypress", (e) => {
    if (e.key === "Enter") {
        sendBtn.click();
    }
});