import { useNavigate } from "react-router-dom";
import DashboardCard from "../components/DashboardCard";

function Home() {

    const navigate = useNavigate();

    return (

        <div className="container mt-5">

            <h1 className="text-center">
                Ticket Monitoring System
            </h1>

            <p className="text-center mb-5">
                Create, Track & Manage Support Tickets
            </p>

            <div className="row">

                <div className="col-md-4">

                    <DashboardCard
                        title="Customer"
                        description="Create and track tickets."
                        buttonText="Enter"
                        onClick={() => navigate("/customer")}
                    />

                </div>

                <div className="col-md-4">

                    <DashboardCard
                        title="Agent"
                        description="Handle assigned tickets."
                        buttonText="Enter"
                        onClick={() => navigate("/agent")}
                    />

                </div>

                <div className="col-md-4">

                    <DashboardCard
                        title="Admin"
                        description="Manage users and categories."
                        buttonText="Enter"
                        onClick={() => navigate("/admin")}
                    />

                </div>

            </div>

        </div>

    );

}

export default Home;