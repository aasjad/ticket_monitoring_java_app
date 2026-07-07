import { useEffect, useState } from "react";
import api from "../services/api";

function CustomerDashboard() {

    const [categories, setCategories] = useState([]);

    const [ticket, setTicket] = useState({
        title: "",
        description: "",
        priority: "LOW",
        reporterId: 9,
        categoryId: ""
    });

   useEffect(() => {
    console.log("Customer Dashboard Loaded");
    loadCategories();
}, []);

    const loadCategories = async () => {
    console.log("Loading categories...");

    try {
        const response = await api.get("/categories");

        console.log("Response:", response);
        console.log("Categories:", response.data);

        setCategories(response.data);

    } catch (error) {
        console.error("Category Error:", error);
    }
};

    const handleChange = (e) => {
        setTicket({
            ...ticket,
            [e.target.name]: e.target.value
        });
    };

    const createTicket = async () => {

        try {

            await api.post("/tickets", ticket);
            const time = new Date().getHours()
            if (time>12){
                alert("Ticket Created Successfully! in afternoon",);

            }
            else{
                alert("Ticket Created SuccessFull in morning")
            }

           
        } catch (error) {
            console.log(error);
            alert("Failed to create ticket");
        }

    };

    return (

        <div className="container mt-5">

            <h2>Create Ticket</h2>

            <input
                className="form-control mb-3"
                placeholder="Title"
                name="title"
                value={ticket.title}
                onChange={handleChange}
            />

            <textarea
                className="form-control mb-3"
                placeholder="Description"
                name="description"
                value={ticket.description}
                onChange={handleChange}
            />
            <h5>Priority:</h5>
            <select
                className="form-control mb-3"
                name="priority"
                value={ticket.priority}
                onChange={handleChange}
            >

                <option>LOW</option>
                <option>MEDIUM</option>
                <option>HIGH</option>

            </select>

            <h5>Category:</h5>
            <select
                className="form-control mb-3"
                name="categoryId"
                value={ticket.categoryId}
                onChange={handleChange}
            >

                <option value="">Select Category</option>

                {categories.map(category => (

                    <option
                        key={category.id}
                        value={category.id}
                    >
                        {category.name}
                    </option>

                ))}

            </select>

            <button
                className="btn btn-success"
                onClick={createTicket}
            >
                Create Ticket
            </button>
        </div>
    );

}

export default CustomerDashboard;