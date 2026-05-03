const BASE_URL = "/api";

window.onload = () => {

    const user = JSON.parse(localStorage.getItem("user"));

    if (user) {
        document.getElementById("name").value = user.name || "";
        document.getElementById("phone").value = user.phone || "";
    }
};

function toggleAddress() {
    const type = document.getElementById("delivery").value;
    document.getElementById("addressDiv").style.display =
        (type === "PICKUP") ? "block" : "none";
}

function toggleAmount() {

    const type = document.getElementById("type").value;

    document.getElementById("amountDiv").style.display =
        (type === "MONEY") ? "block" : "none";

    document.getElementById("foodDiv").style.display =
        (type === "FOOD") ? "block" : "none";
}

function toggleFoodFields() {
    const type = document.getElementById("type").value;
    const foodDiv = document.getElementById("foodDiv");

    foodDiv.style.display = (type === "FOOD") ? "block" : "none";
}

document.getElementById("donateForm").addEventListener("submit", async (e) => {
    e.preventDefault();

    const user = JSON.parse(localStorage.getItem("user"));
    const ngoId = localStorage.getItem("ngoId");

    const donationType = document.getElementById("type").value;

    let quantity = null;
    let foodType = null;
    let amount = null;

    if (donationType === "FOOD") {
        quantity = document.getElementById("quantity").value;
        foodType = document.getElementById("foodType").value;

        if (!quantity || !foodType) {
            alert("Enter food quantity and type");
            return;
        }
    }

    if (donationType === "MONEY") {
        amount = document.getElementById("amount").value;

        if (!amount || amount <= 0) {
            alert("Enter valid amount");
            return;
        }
    }

    const body = {
        donationType: donationType,
        deliveryType: document.getElementById("delivery").value,
        pickupAddress: document.getElementById("address").value || null,
        pickupDateTime: document.getElementById("date").value,
        contactName: document.getElementById("name").value,
        contactPhone: document.getElementById("phone").value,

        // ✅ NEW FIELDS
        quantity: quantity,
        foodType: foodType,
        amount: amount
    };

    const res = await fetch(`${BASE_URL}/donations?userId=${user.id}&ngoId=${ngoId}`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(body)
    });

    if (res.ok) {
        alert("Donation request sent ✅");
        window.location.href = "user-dashboard.html";
    } else {
        alert("Error");
    }
});