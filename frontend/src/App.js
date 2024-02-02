import { Routes, Route } from "react-router-dom";
import Home from "./Pages/Home";
import NotFound from "./Pages/NotFound";
import Station from "./Pages/Station";
import Bike from "./Pages/Bike";
import Bikes from "./Pages/Bikes";
import RentBike from "./Pages/RentBike";
import ReturnBike from "./Pages/ReturnBike";
import PayMent from "./Pages/Payment";
import ViewBike from "./Pages/ViewBike";
import Invoices from "./Pages/Invoices";

function App() {

  return (
    <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/station/:id" element={<Station />} />
      <Route path="/bikes/:id" element={<Bike />} />
      <Route path="/bikes" element={<Bikes />} />
      <Route path="/rentbike/:id" element={<RentBike />} />
      <Route path="/payment/:id" element={<PayMent />} />
      <Route path="/viewbike" element={<ViewBike />} />
      <Route path="/returnbike" element={< ReturnBike />} />
      <Route path="/return/:stationId" element={< Invoices />} />
      <Route path="*" element={<NotFound />} />
    </Routes>
  );
}

export default App;
