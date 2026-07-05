import { useNavigate } from "react-router-dom";

function AgentDashboard() {

    const navigate = useNavigate();

    return (

        <div className="container mt-5">

            <div className="d-flex justify-content-between align-items-center mb-4">

                <h2>🎧 Agent Dashboard</h2>

                <button
                    className="btn btn-secondary"
                    onClick={() => navigate("/")}
                >
                    Home
                </button>

            </div>

            <div className="card shadow">

                <div className="card-header bg-primary text-white">
                    Assigned Tickets
                </div>

                <div className="card-body">

                    <table className="table table-bordered table-hover">

                        <thead>

                        <tr>

                            <th>ID</th>
                            <th>Title</th>
                            <th>Customer</th>
                            <th>Priority</th>
                            <th>Status</th>
                            <th>Action</th>

                        </tr>

                        </thead>

                        <tbody>

                        <tr>

                            <td>1</td>
                            <td>Laptop Issue</td>
                            <td>Rahul Kumar</td>
                            <td>HIGH</td>
                            <td>OPEN</td>

                            <td>

                                <button className="btn btn-warning btn-sm me-2">
                                    In Progress
                                </button>

                                <button className="btn btn-success btn-sm">
                                    Resolve
                                </button>

                            </td>

                        </tr>

                        </tbody>

                    </table>

                </div>

            </div>

        </div>

    );

}

export default AgentDashboard;