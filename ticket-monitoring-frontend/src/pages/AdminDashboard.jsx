import { useEffect, useState } from "react";
import api from "../services/api";

function AdminDashboard() {

    const [tickets, setTickets] = useState([]);
    const [agents, setAgents] = useState([]);
    const [selectedAgent, setSelectedAgent] = useState({});

    useEffect(() => {
        loadTickets();
        loadAgents();
    }, []);

    const loadTickets = async () => {
        try {
            const response = await api.get("/tickets");
            setTickets(response.data);
        } catch (error) {
            console.log(error);
        }
    };

    const loadAgents = async () => {
        try {
            const response = await api.get("/users/agents");
            setAgents(response.data);
        } catch (error) {
            console.log(error);
        }
    };

    const assignTicket = async (ticketId) => {

        const assigneeId = selectedAgent[ticketId];

        if (!assigneeId) {
            alert("Please select an agent");
            return;
        }

        try {

            await api.put(`/tickets/${ticketId}/assign`, {
                assigneeId: Number(assigneeId)
            });

            alert("Ticket Assigned Successfully");

            loadTickets();

        } catch (error) {
            console.log(error);
            alert("Assignment Failed");
        }
    };

    return (

        <div className="container mt-5">

            <h2 className="mb-4">
                Admin Dashboard
            </h2>

            <table className="table table-bordered table-hover">

                <thead className="table-dark">

                <tr>

                    <th>ID</th>
                    <th>Title</th>
                    <th>Reporter</th>
                    <th>Priority</th>
                    <th>Status</th>
                    <th>Assigned Agent</th>
                    <th>Select Agent</th>
                    <th>Action</th>

                </tr>

                </thead>

                <tbody>

                {tickets.map(ticket => (

                    <tr key={ticket.id}>

                        <td>{ticket.id}</td>

                        <td>{ticket.title}</td>

                        <td>{ticket.reporterName}</td>

                        <td>{ticket.priority}</td>

                        <td>{ticket.status}</td>

                        <td>{ticket.assigneeName || "Not Assigned"}</td>

                        <td>

                            <select

                                className="form-select"

                                value={selectedAgent[ticket.id] || ""}

                                onChange={(e) =>
                                    setSelectedAgent({
                                        ...selectedAgent,
                                        [ticket.id]: e.target.value
                                    })
                                }

                            >

                                <option value="">
                                    Select Agent
                                </option>

                                {agents.map(agent => (

                                    <option
                                        key={agent.id}
                                        value={agent.id}
                                    >
                                        {agent.fullName}
                                    </option>

                                ))}

                            </select>

                        </td>

                        <td>

                            <button
                                className="btn btn-primary"
                                onClick={() => assignTicket(ticket.id)}
                            >
                                Assign
                            </button>

                        </td>

                    </tr>

                ))}

                </tbody>

            </table>

        </div>

    );

}

export default AdminDashboard;