import Vehicle from "./Vehicle";

function VehicleList() {
  return (
    <div className="bg-light p-4 mt-4">
      <header className="text-center p-4 text-info">
        <h1 className="text-dark">All Available Vehicles</h1>
      </header>
      <div className="container">
        <Vehicle showAvailableOnly={true} />
      </div>
    </div>
  );
}

export default VehicleList;