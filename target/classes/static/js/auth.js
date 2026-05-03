const BASE_URL = "/api";

/* =======================
   LOGIN
======================= */
const loginForm = document.getElementById("loginForm");

if (loginForm) {
    loginForm.addEventListener("submit", async (e) => {
        e.preventDefault();

        const body = {
            email: document.getElementById("email").value,
            password: document.getElementById("password").value
        };

        const res = await fetch(`${BASE_URL}/auth/login`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(body)
        });

        const data = await res.json();

        if (res.ok) {
            alert("Login successful");

            localStorage.setItem("user", JSON.stringify(data));

            if (data.role === "ADMIN") {
                window.location.href = "admin-dashboard.html";
            } else if (data.role === "NGO") {
                window.location.href = "ngo-dashboard.html";
            } else {
                window.location.href = "user-dashboard.html";
            }
        } else {
            alert(data.message || "Login failed");
        }
    });
}


/* =======================
   SIGNUP
======================= */
const signupForm = document.getElementById("signupForm");

if (signupForm) {
    signupForm.addEventListener("submit", async (e) => {
        e.preventDefault();

        const body = {
            name: document.getElementById("name").value,
            email: document.getElementById("email").value,
            password: document.getElementById("password").value
        };

        try {
            const res = await fetch(`${BASE_URL}/auth/signup`, {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(body)
            });

            if (res.ok) {
                alert("Signup successful! Please login.");
                window.location.href = "login.html";
            } else {
                const data = await res.json();
                alert(data.message || "Signup failed");
            }

        } catch (error) {
            console.error("Error:", error);
            alert("Server error");
        }
    });
}


/* =======================
   👁 TOGGLE PASSWORD (ADD HERE)
======================= */
function togglePassword(id, icon) {

    const input = document.getElementById(id);

    if (input.type === "password") {
        input.type = "text";
        icon.textContent = "🙈";
    } else {
        input.type = "password";
        icon.textContent = "👁";
    }
}