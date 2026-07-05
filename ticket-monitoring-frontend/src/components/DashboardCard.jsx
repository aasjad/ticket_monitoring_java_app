function DashboardCard({ title, description, buttonText, onClick }) {
  return (
    <div className="card shadow p-4">
      <h3>{title}</h3>

      <p>{description}</p>

      <button
        className="btn btn-primary"
        onClick={onClick}
      >
        {buttonText}
      </button>
    </div>
  );
}

export default DashboardCard;