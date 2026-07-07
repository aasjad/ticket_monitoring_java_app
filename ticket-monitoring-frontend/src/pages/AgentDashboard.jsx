import { useEffect, useState } from "react";
import api from "../services/api";

function AgentDashboard() {

    const [tickets, setTickets] = useState([]);

    useEffect(() => {
        loadTickets();
    }, []);

    const loadTickets = async () => {
        try {
            const response = await api.get("/tickets");
            setTickets(response.data);
        } catch (error) {
            console.log(error);
        }
    };

    const updateStatus = async (id, status) => {

        try {

            await api.put(`/tickets/${id}/status`, {
                status: status
            });

            loadTickets();

        } catch (error) {
            console.log(error);
        }

    };

    return (

        <div className="container mt-5">

            <h2>Assigned Tickets</h2>

            <table className="table table-bordered">

                <thead>

                <tr>
                    <th>ID</th>
                    <th>Title</th>
                    <th>Priority</th>
                    <th>Status</th>
                    <th>Action</th>
                </tr>

                </thead>

                <tbody>

                {tickets.map(ticket => (

                    <tr key={ticket.id}>

                        <td>{ticket.id}</td>
                        <td>{ticket.title}</td>
                        <td>{ticket.priority}</td>
                        <td>{ticket.status}</td>

                        <td>

                            <button
                                className="btn btn-warning btn-sm me-2"
                                onClick={() => updateStatus(ticket.id, "IN_PROGRESS")}
                            >
                                Start
                            </button>

                            <button
                                className="btn btn-success btn-sm"
                                onClick={() => updateStatus(ticket.id, "RESOLVED")}
                            >
                                Resolve
                            </button>

                        </td>

                    </tr>

                ))}

                </tbody>

            </table>

        </div>

    );

}

export default AgentDashboard;