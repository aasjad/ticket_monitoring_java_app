import { useNavigate } from "react-router-dom";

function AdminDashboard() {

    const navigate = useNavigate();

    return (

        <div className="container mt-5">

            <div className="d-flex justify-content-between align-items-center">

                <h2>🛠 Admin Dashboard</h2>

                <button
                    className="btn btn-secondary"
                    onClick={() => navigate("/")}
                >
                    Home
                </button>

            </div>

            <div className="row mt-4">

                <div className="col-md-4">

                    <div className="card shadow">

                        <div className="card-body">

                            <h4>👥 Users</h4>

                            <p>
                                Manage Customers & Agents
                            </p>

                            <button className="btn btn-primary w-100">
                                Manage Users
                            </button>

                        </div>

                    </div>

                </div>

                <div className="col-md-4">

                    <div className="card shadow">

                        <div className="card-body">

                            <h4>📂 Categories</h4>

                            <p>
                                Add or Remove Categories
                            </p>

                            <button className="btn btn-success w-100">
                                Manage Categories
                            </button>

                        </div>

                    </div>

                </div>

                <div className="col-md-4">

                    <div className="card shadow">

                        <div className="card-body">

                            <h4>🎫 Tickets</h4>

                            <p>
                                View All Tickets
                            </p>

                            <button className="btn btn-warning w-100">
                                View Tickets
                            </button>

                        </div>

                    </div>

                </div>

            </div>

        </div>

    );

}

export default AdminDashboard;