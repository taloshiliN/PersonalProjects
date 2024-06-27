import "./App.css";
import Header from "./components/Header";
import { Note } from "./models/note.model";
import React, { useState } from "react";

function App() {
  const [notes, setNotes] = useState<Note[]>([
    {
      id: new Date().toString(),
      title: "Meetings",
      text: "Schedule meeting with UI/UX Team",
      color: "adfdfdf",
      date: new Date().toString(),
    },
  ]);

  return;
  <>
    <Header></Header>
  </>;
}

export default App;
