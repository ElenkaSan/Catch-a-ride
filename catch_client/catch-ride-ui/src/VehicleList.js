import Vehicle from "./Vehicle";

function VehicleList() {
  return (
    <div>
      <header className="text-center p-4 text-info">
        <h1>All Available Vehicles</h1>
      </header>
      <div className="container">
        <Vehicle showAvailableOnly={true} />
      </div>
    </div>
  );
}

export default VehicleList;
