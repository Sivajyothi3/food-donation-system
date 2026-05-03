# 🍱 Food Donation System

A full-stack web application designed to reduce food waste and connect donors with NGOs efficiently.

---

## 🌍 Project Overview

The **Food Donation System** enables users to donate food or money to verified NGOs.
It ensures that surplus food reaches people in need while maintaining transparency between donors and organizations.

This platform provides separate dashboards for **Users, NGOs, and Admin**, making the system structured, scalable, and practical for real-world usage.

---

## 🚀 Key Features

### 👤 User Features

* User Registration & Login
* Browse verified NGOs
* Donate:

  * 🍲 Food (with type: Cooked / Raw)
  * 💰 Money
* Provide:

  * Quantity (number of people)
  * Pickup / Self delivery option
  * Date & time
* Track donation status:

  * Pending / Approved / Rejected
* View complete donation history
* Manage profile & change password

---

### 🏢 NGO Features

* NGO Registration with document verification
* Admin approval required before access
* NGO Dashboard:

  * View incoming donation requests
  * Accept / Reject donations
  * Add response message
* View donation history
* Access donor details:

  * Food type
  * Quantity
  * Contact & address
* Profile management

---

### 🛡️ Admin Features

* Approve / Reject NGO registrations
* Verify uploaded NGO documents
* Maintain platform authenticity
* Control NGO access to system

---

## 🎯 Real-World Impact

* ♻️ Reduces food wastage
* 🤝 Connects donors with trusted NGOs
* 🍽️ Helps distribute food to needy people
* 📊 Ensures transparency in donation process
* 🏢 Enables NGOs to efficiently manage donations

---

## 🛠️ Tech Stack

### Frontend

* HTML5
* CSS3
* JavaScript (Vanilla JS)

### Backend

* Spring Boot (Java)
* REST APIs

### Database

* MySQL

### Tools & Technologies

* Maven
* Git & GitHub
* Postman (API Testing)

---

## 🔐 Security Features

* Sensitive data protected using:

  * `.gitignore`
  * Spring Profiles (`application-local.properties`)
* Separation of environment configurations

---

## 📸 Screenshots

### 🏠 Home Page

<img width="1861" height="906" alt="Screenshot 2026-05-03 094416" src="https://github.com/user-attachments/assets/8560e06f-4454-43c1-ae50-c2197f6715f2" />


### 🔐 Login Page

<img width="1886" height="879" alt="Screenshot 2026-05-03 094534" src="https://github.com/user-attachments/assets/4e5f779a-f1d2-465e-89c7-69da9ad6b082" />


### 📝 Signup Page

<img width="1823" height="850" alt="Screenshot 2026-05-03 094549" src="https://github.com/user-attachments/assets/d263438b-8686-4571-9f13-32b601165e50" />


### 🏢 NGO Signup Page

<img width="1856" height="888" alt="Screenshot 2026-05-03 094516" src="https://github.com/user-attachments/assets/12254553-dfe2-4b42-88f0-90bc387c69f7" />


### 👤 User Dashboard

<img width="1798" height="886" alt="Screenshot 2026-05-03 094711" src="https://github.com/user-attachments/assets/7b7d87c7-5f61-43bc-a957-67241bc66abc" />


### 🎁 Donate Page

<img width="1824" height="838" alt="Screenshot 2026-05-03 100711" src="https://github.com/user-attachments/assets/4505843c-df05-4e4f-9b52-daf41298c84d" />


### 📊 User Donation History

<img width="1815" height="759" alt="Screenshot 2026-05-03 094927" src="https://github.com/user-attachments/assets/8c83b6c9-610a-434e-b787-363a30a8681e" />


### 👤 User Profile

<img width="1767" height="863" alt="Screenshot 2026-05-03 094948" src="https://github.com/user-attachments/assets/d23e2f12-ec4d-4390-b662-6537343972ae" />


### 🛡️ Admin Dashboard

<img width="1817" height="868" alt="Screenshot 2026-05-03 100455" src="https://github.com/user-attachments/assets/aeff4ba4-d766-4d5c-8f53-4621c25f3e3c" />
<img width="1764" height="827" alt="Screenshot 2026-05-03 100558" src="https://github.com/user-attachments/assets/4c07c0c2-9797-4556-b542-4a7e1fe75e82" />
<img width="1825" height="826" alt="Screenshot 2026-05-03 100620" src="https://github.com/user-attachments/assets/ebdcfd2c-242b-4322-870c-96c46650aa98" />


### 🏢 NGO Dashboard

<img width="1760" height="896" alt="Screenshot 2026-05-03 101700" src="https://github.com/user-attachments/assets/8714b11f-c3f5-4d79-a582-27167a6380db" />


### 📜 NGO Donation History

<img width="1859" height="870" alt="Screenshot 2026-05-03 101715" src="https://github.com/user-attachments/assets/6cc36972-866e-4270-84da-4f19bc839464" />


### 🏢 NGO Profile

<img width="1828" height="897" alt="Screenshot 2026-05-03 101737" src="https://github.com/user-attachments/assets/5ece4c18-98d2-4103-ace2-6b5e88caa8a0" />


---

## ⚙️ How to Run the Project

### 1️⃣ Clone Repository

```bash
git clone https://github.com/your-username/food-donation-system.git
cd food-donation-system
```

### 2️⃣ Configure Database

Create:

```
application-local.properties
```

Add:

```
spring.datasource.url=jdbc:mysql://localhost:3306/food_donation
spring.datasource.username=your_username
spring.datasource.password=your_password
```

---

### 3️⃣ Run Backend

```bash
mvn spring-boot:run
```

---

### 4️⃣ Open Frontend

Open `index.html` in browser

---

## 🌟 Future Enhancements

* Payment Gateway Integration
* Email / SMS Notifications
* Real-time tracking of donations
* Mobile App version
* Location-based NGO suggestions

---

## 👩‍💻 Author

**Siva Jyothi**
GitHub: https://github.com/Sivajyothi3

---

## ⭐ Conclusion

This project demonstrates:

* Full-stack development skills
* Real-world problem solving
* Clean UI & structured dashboards
* Secure configuration handling

---

👉 A complete solution for connecting donors and NGOs to build a better society.
