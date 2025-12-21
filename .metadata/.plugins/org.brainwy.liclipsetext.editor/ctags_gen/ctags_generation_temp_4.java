	<!DOCTYPE html>
	<html lang="en">
	<head>
	<meta charset="UTF-8">
	<title>Farmer Registration | KrishiSahayak</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
	<link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">
	
	<style>
	*{box-sizing:border-box;font-family:Poppins}
	body{
	    background:#f4f7f0;
	    min-height:100vh;
	    display:flex;
	    justify-content:center;
	    align-items:center;
	}
	.wizard-container{
	    width:100%;
	    max-width:480px;
	    background:#fff;
	    border-radius:20px;
	    box-shadow:0 10px 30px rgba(0,0,0,.15);
	    overflow:hidden;
	}
	.wizard-header{
	    background:#7cb62f;
	    color:#fff;
	    text-align:center;
	    padding:25px;
	}
	.progress-wrapper{
	    display:flex;
	    justify-content:center;
	    margin-top:15px;
	}
	.step-indicator{
	    width:35px;height:35px;
	    border-radius:50%;
	    background:rgba(255,255,255,.4);
	    display:flex;
	    align-items:center;
	    justify-content:center;
	    font-weight:600;
	    margin:0 10px;
	}
	.step-indicator.active{background:#fff;color:#7cb62f}
	.step-indicator.completed{background:#2c3e50;color:#fff}
	.form-body{padding:25px}
	.step-content{display:none}
	.step-content.active{display:block}
	input,select{
	    width:100%;
	    padding:12px;
	    margin-bottom:12px;
	    border-radius:8px;
	    border:1px solid #ddd;
	}
	.btn-action{
	    width:100%;
	    padding:14px;
	    background:#7cb62f;
	    border:none;
	    color:#fff;
	    font-size:16px;
	    border-radius:10px;
	    cursor:pointer;
	}
	.btn-secondary{
	    margin-top:10px;
	    background:#f0f0f0;
	    color:#333;
	}
	.otp-inputs{
	    display:flex;
	    justify-content:center;
	    gap:10px;
	}
	.otp-box{
	    width:45px;height:45px;
	    font-size:20px;
	    text-align:center;
	}
	.error{
	    color:red;
	    font-size:13px;
	    margin-bottom:8px;
	}
	</style>
	</head>
	
	<body>
	
	<div class="wizard-container">
	
	<div class="wizard-header">
	<h2>Create Account</h2>
	<p>Join the KrishiSahayak Community</p>
	
	<div class="progress-wrapper">
	<div class="step-indicator active">1</div>
	<div class="step-indicator">2</div>
	<div class="step-indicator">3</div>
	</div>
	</div>
	
	<div class="form-body">
	
	<!-- ================= STEP 1 ================= -->
	<form id="formStep" action="generatedOtp" method="post">
	
	<div class="step-content active" id="step1">
	<h3><i class="fas fa-user-circle"></i> Personal Details</h3>
	
	<input id="fullName" name="name" placeholder="Full Name">
	<div id="nameError" class="error"></div>
	
	<input id="phone" name="phone" placeholder="Phone Number" maxlength="10">
	<div id="phoneError" class="error"></div>
	
	<input id="email" name="email" placeholder="Email">
	<div id="emailError" class="error"></div>
	
	<input id="password" name="passwordHash" type="password" placeholder="Password">
	<div id="passwordError" class="error"></div>
	
	<input id="confirmPassword" type="password" placeholder="Confirm Password">
	<div id="confirmError" class="error"></div>
	
	<button type="submit" class="btn-action" onclick="submitStep1(this)">
	Next Step <i class="fas fa-arrow-right"></i>
	</button>
	</div>
	</form>
	
	<!-- ================= STEP 2 & 3 ================= -->
	<form id="formStep2" action="sussregister" method="post">
	
	<!-- STEP 2 -->
	<div class="step-content" id="step2">
	<h3><i class="fas fa-shield-alt"></i> Verify Phone</h3>
	
	<div class="otp-inputs">
	<input class="otp-box" maxlength="1">
	<input class="otp-box" maxlength="1">
	<input class="otp-box" maxlength="1">
	<input class="otp-box" maxlength="1">
	</div>
	
	<button type="button" class="btn-action" onclick="validateStep2()">
	Verify & Proceed
	</button>
	
	<button type="button" class="btn-action btn-secondary" onclick="showStep(1)">
	Back
	</button>
	</div>
	
	<!-- STEP 3 -->
	<div class="step-content" id="step3">
	<h3><i class="fas fa-map-marker-alt"></i> Complete Profile</h3>
	
	<select name="language">
	<option value="">Preferred Language</option>
	<option value="en">English</option>
	<option value="hi">Hindi</option>
	</select>
	
	<select name="state">
	<option value="">State</option>
	<option>Punjab</option>
	<option>Uttar Pradesh</option>
	</select>
	
	<button type="submit" class="btn-action">
	Complete Registration <i class="fas fa-rocket"></i>
	</button>
	</div>
	
	</form>
	</div>
	</div>
	
	<script>
	function showStep(step){
	document.querySelectorAll(".step-content").forEach(s=>s.classList.remove("active"));
	document.getElementById("step"+step).classList.add("active");
	
	document.querySelectorAll(".step-indicator").forEach((dot,i)=>{
	dot.classList.remove("active","completed");
	if(i+1===step) dot.classList.add("active");
	if(i+1<step) dot.classList.add("completed");
	});
	}
	
	function submitStep1(btn){
	let valid=true;
	
	const name=fullName.value.trim();
	const phone=phone.value.trim();
	const email=email.value.trim();
	const pwd=password.value;
	const cpwd=confirmPassword.value;
	
	document.querySelectorAll(".error").forEach(e=>e.innerText="");
	
	if(name.length<3){nameError.innerText="Enter valid name";valid=false;}
	if(!/^[6-9]\d{9}$/.test(phone)){phoneError.innerText="Invalid phone number";valid=false;}
	if(email && !/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)){emailError.innerText="Invalid email";}
	if(!/^(?=.*[A-Z])(?=.*[a-z])(?=.*\d).{6,}$/.test(pwd)){
	passwordError.innerText="Password must contain uppercase, lowercase & number";
	valid=false;
	}
	if(pwd!==cpwd){confirmError.innerText="Passwords do not match";valid=false;}
	
	if(!valid) return;
	
	btn.disabled=true;
	btn.innerHTML="Sending OTP...";
	
	setTimeout(()=>{
	btn.disabled=false;
	btn.innerHTML="Next Step";
	showStep(2);
	alert("Mock OTP: 1234");
	},800);
	}
	
	document.querySelectorAll(".otp-box").forEach((box,i,arr)=>{
	box.addEventListener("input",()=>{
	box.value=box.value.replace(/\D/g,'');
	if(box.value && arr[i+1]) arr[i+1].focus();
	});
	});
	
	function validateStep2(){
	const otp=[...document.querySelectorAll(".otp-box")].map(i=>i.value).join("");
	if(otp!=="1234"){alert("Invalid OTP");return;}
	showStep(3);
	}
	</script>
	
	</body>
	</html>
