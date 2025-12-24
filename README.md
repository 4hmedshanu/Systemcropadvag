

# 🌾 Smart Crop Advisory System (Krishi Sahayak)

> **A Holistic AI-Powered Agricultural Decision Support System**

![Java](https://img.shields.io/badge/Java-21-orange?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen?logo=springboot)
![Python](https://img.shields.io/badge/Python-3.14-blue?logo=python)
![FastAPI](https://img.shields.io/badge/Microservices-FastAPI-teal?logo=fastapi)
![TensorFlow](https://img.shields.io/badge/AI-TensorFlow%20%7C%20Scikit--Learn-red?logo=tensorflow)
![MySQL](https://img.shields.io/badge/Database-MySQL-blue?logo=mysql)
![WhatsApp](https://img.shields.io/badge/Integrations-Twilio%20WhatsApp-25D366?logo=whatsapp)

---

## 📌 Project Overview

**Krishi Sahayak** is a comprehensive agricultural ecosystem designed to bridge the gap between advanced technology and farmers. It integrates **Machine Learning, Deep Learning, and IoT-ready architectures** to provide real-time, data-driven advice.

Unlike simple advisory apps, this system uses a **Microservices Architecture** where the AI Brain (Python) operates independently from the Application Core (Java Spring Boot), ensuring scalability and high performance.

### 🌟 Key Features
* **🌱 Precision Crop Recommendation:** Stacking Ensemble ML to suggest the best crop for specific soil conditions.
* **🧪 Intelligent Fertilizer Guide:** Hybrid (ML + Rule-based) system for nutrient optimization.
* **🌿 Visual Disease Doctor:** CNN-based image analysis to detect plant diseases instantly.
* **🤖 AI Agronomist Chatbot:** Generative AI-powered chat for solving queries in local languages.
* **📢 Real-time Alerts:** Automated WhatsApp alerts for weather changes and disease outbreaks.
* **🌍 Community Blog:** A platform for farmers and experts to share knowledge.

---

## 📂 System Architecture & File Structure

This repository follows a **Microservices-oriented** structure, separating the Java Backend, Frontend, and Python AI modules.

```bash
Systemcropadvag/
├── LICENSE
├── README.md
│
├── 📂 SCAS DATA/                          # Raw Datasets
│   ├── 📂 crop_recommendation_data/
│   │   └── crop_recommendation_dataset.csv
│   └── 📂 fertilizer_recommendation_data/
│       └── fertlizer_recommendation_dataset.csv
│
├── 📂 scas ML models/                     # [AI/ML ENGINE] Python Logic
│   ├── 📂 EDA/                            # Exploratory Data Analysis
│   │   ├── EDA_crop_data.ipynb
│   │   └── EDA_fertilizer_data.ipynb
│   │
│   ├── 📂 crop recommendation/            # Model 1: Crop Selection
│   │   ├── crop_api.py                    # FastAPI Entry Point
│   │   ├── crop_model_final.pkl           # Trained Model
│   │   ├── model 5.0.ipynb                # Training Notebook
│   │   ├── scaler_final.pkl
│   │   ├── label_encoder_final.pkl
│   │   └── requirements.txt
│   │
│   ├── 📂 fertilizer recommendation/      # Model 2: Fertilizer Logic
│   │   ├── fertilizer_api.py              # FastAPI Entry Point
│   │   ├── fertilizer_model_final.pkl     # Trained Model
│   │   ├── fert_model_3.1.ipynb           # Training Notebook
│   │   └── requirements.txt
│   │
│   └── 📂 disease detection/              # Model 3: Image Processing
│       ├── disease_api.py                 # FastAPI Entry Point
│       ├── final_disease_model.h5         # Trained CNN Weights (Deep Learning)
│       ├── plant_disease_model_notebook.ipynb
│       ├── class_indices.json             # Disease Labels
│       └── requirements.txt
│
├── 📂 Smart_crop_advisory_backend/        # [BACKEND API] Java Spring Boot
│   ├── pom.xml
│   ├── backend_sql.sql                    # Database Schema
│   └── src/main/java/com/example_Backend/
│       ├── SmartCropAdvisoryBackendApplication.java
│       ├── 📂 ConfigSecurity/             # Auth & Security
│       ├── 📂 Controllers/                # REST Controllers (Chat, User, Blog)
│       ├── 📂 DTO/                        # Data Transfer Objects
│       ├── 📂 Entity/                     # DB Models (User, Crop, Soil, etc.)
│       ├── 📂 Repository/                 # JPA Repositories
│       └── 📂 entityServices/             # Business Logic (Email, WhatsApp)
│
└── 📂 Smart_crop_advisory_system_Frouent/ # [FRONTEND WEB APP] Java Spring Boot
    ├── pom.xml
    └── src/main/
        ├── java/com/example_Backend/
        │   ├── 📂 endpoint/               # Web Controllers (Views)
        │   ├── 📂 entity/
        │   └── 📂 repository/             # External API Calls (Feign Clients)
        │
        └── resources/
            ├── application.properties
            ├── 📂 static/                 # Static Assets
            │   ├── 📂 cssfile/            # Styles (style.css, blog_style.css)
            │   ├── 📂 jsfile/             # Scripts (script.js, chatbot.js)
            │   ├── 📂 img/                # Images & Icons
            │   └── 📂 gif/
            │
            └── 📂 templates/              # HTML Views (Thymeleaf)
                ├── index.html
                ├── login.html
                ├── signup.html
                ├── crop.html              # Connects to Crop API
                ├── fertilizer.html        # Connects to Fertilizer API
                ├── disease.html           # Connects to Disease API
                ├── weather.html
                └── blog.html
```

---

## 🚀 Module 1: The Backend (Java Spring Boot)

The central nervous system of Krishi Sahayak, handling user data, security, and communication.

### Tech Stack

* **Framework:** Spring Boot 3.x (Java 21)
* **Database:** MySQL 8.0 (Hibernate/JPA)
* **Security:** Spring Security (RBAC)
* **Messaging:** Twilio SDK (WhatsApp)

### Key Capabilities

1. **User Management:** Secure Registration, Login, and Profile updates.
2. **Blog Platform:** Full CRUD capabilities for community posts and comments.
3. **WhatsApp Alerts:** Push notification service for critical agricultural alerts.
4. **History Tracking:** Saves user's past predictions (Soil reports, Disease scans) for future reference.

### 🔌 Core API Endpoints

| Method | Endpoint | Description |
| --- | --- | --- |
| `POST` | `/User` | Register new user |
| `GET` | `/User/login` | Authenticate user |
| `POST` | `/whatsapp/sandbox/join` | Connect user to WhatsApp alerts |
| `POST` | `/blog/createBlog` | Publish community post |

---

## 🧠 Module 2: The AI Brain (Python Microservices)

The intelligence layer consists of three independent models exposed via **FastAPI**.

<details>
<summary><b>1️⃣ Crop Recommendation Model (Port 8000)</b></summary>

* **Goal:** Recommends the most biologically suitable crop.
* **Algorithm:** **Stacking Ensemble Classifier**
* *Base Learners:* Random Forest, XGBoost, KNN.
* *Meta Learner:* Logistic Regression.


* **Inputs:** N, P, K, Temperature, Humidity, pH, Rainfall.
* **Performance:** ~99.3% Accuracy on test data.
* **Unique Feature:** Uses feature scaling (`StandardScaler`) to normalize rainfall and chemical data for higher precision.

</details>

<details>
<summary><b>2️⃣ Fertilizer Recommendation Model (Port 8001)</b></summary>

* **Goal:** Suggests specific fertilizer products and dosages.
* **Algorithm:** **Voting Classifier (Soft Voting)** combining Random Forest and Gradient Boosting.
* **Inputs:** Soil Type, Crop Type, N, P, K, Moisture, Weather data.
* **Logic Layer:** Includes a post-processing rule engine. *Example: If the model suggests 'Complex NPK' but Nitrogen is critically low, the system overrides to suggest 'Urea'.*
* **Performance:** ~95.75% Accuracy.

</details>

<details>
<summary><b>3️⃣ Plant Disease Detection Model (Port 8002)</b></summary>

* **Goal:** Identifies 34 unique plant diseases from leaf images.
* **Architecture:** **MobileNetV2** (Transfer Learning).
* *Optimization:* Trained using **Mixed Precision (float16)** for faster inference.


* **Input:** 224x224 RGB Images.
* **Performance:** ~94-96% Validation Accuracy.
* **Classes:** Includes Blights, Rusts, Mildews, and Healthy states for crops like Tomato, Potato, Corn, and Rice.

</details>

---

## 💻 Installation & Setup Guide

### Phase 1: Database Setup

1. Install **MySQL Server**.
2. Create a database named `smart_crop_advisory_db`.
3. Run the script `backend_sql.sql` (located in the backend folder) to initialize tables.

### Phase 2: AI Microservices (Python)

*Requires Python 3.9+*

```bash
# Navigate to models folder
cd "scas ML models"

# Create Environment
python -m venv venv
source venv/bin/activate  # (Windows: venv\Scripts\activate)

# Install Dependencies
pip install -r requirements.txt

# Run the 3 API Servers (Open 3 separate terminals)
uvicorn "crop recommendation.crop_api":app --port 8000 --reload
uvicorn "fertilizer recommendation.fertilizer_api":app --port 8001 --reload
python "disease detection/disease_api.py"  # Runs on 8002

```

### Phase 3: Backend & Frontend (Java)

*Requires JDK 21+ and Maven*

1. **Configure:** Update `src/main/resources/application.properties` with your MySQL credentials and Twilio/OpenAI keys.
2. **Run:**
```bash
cd Smart_crop_advisory_system_Frouent
./mvnw spring-boot:run

```


3. **Access:** Open Browser -> `http://localhost:8080` (or the configured port).

---

## ⚠️ Disclaimer

> **Academic Project:** This system is developed for the **B.Tech Minor Project** evaluation.
> * **Advisory Nature:** All AI predictions are suggestions based on data patterns. Farmers should consult agricultural extension officers before large-scale implementation.
> * **Data:** Models are trained on public datasets (Kaggle/PlantVillage) and may require fine-tuning for specific local soil variations.
> 
> 

---

## 👨‍💻 Meet the Team

| Developer | Role | Contact |
| --- | --- | --- |
| **Shanu Ahmed** | Full Stack & AI Lead | 📧 [Email](mailto:shanuahmed03@gmail.com) |
| **Abhay Maurya** | Backend Architect | 📧 [Email](mailto:abhay.maurya0303@gmail.com) |
| **Amit Yadav** | Database & Security | 📧 [Email](mailto:amity3289@gmail.com) |
| **Varun Rana** | Frontend Integration | 📧 [Email](mailto:varunrana1902@example.com) |
| **Samir Ahmad** | Testing & QA | 📧 [Email](mailto:azharahmad5310@gmail.com) |

<p align="center">
<b>🌟 Made with ❤️ for the Indian Farming Community 🌟</b>
</p>

`