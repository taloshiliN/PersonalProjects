import { useState } from "react";

interface Props {
  items: string[];
  heading: string;
  //(itemL string) => void
  onSelectItem: (item: string) => void;
}

function ListGroup({ items, heading, onSelectItem }: Props) {
  //This is a Hook, a state hook
  const [selectedIndex, setSelectedIndex] = useState(-1);
  return (
    <>
      <h1>{heading}</h1>
      {items.length === 0 && <p>No Item found</p>}
      <ul className="list-group">
        {items.map((item, index) => (
          <li
            className={
              selectedIndex === index
                ? "list-group-item active"
                : "list-group-item"
            }
            key={item}
            onClick={() => {
              setSelectedIndex(index);
              onSelectItem(item);
            }}
          >
            {item}
          </li>
        ))}
        ;
      </ul>
    </>
  );
}

export default ListGroup;
//difference between props and state
//a prop is an input passed to a component while a state
//is the data managed by a component
//prop is similar to arguments of a function and it is immutable or treated as immutable
//states are similar to local variables
//state are treated as mutable
