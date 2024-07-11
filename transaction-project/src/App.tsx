import { BrowserRouter, Routes, Route } from "react-router-dom";
import Home from "./pages/Home";
import Events from "./pages/Events";
import About from "./pages/About";

function App() {
  return (
    <div>
      <BrowserRouter>
        <Routes>
          <Route index element={<Home />} />
          <Route index element={<Events />} />
          <Route index element={<About />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
