import * as React from "react";
import {Container};

interface IHeaderProps {}

const Header: React.FunctionComponent<IHeaderProps> = (props) => {
  return (
    <Navbar fixed="top" bg="dark" variant="dark">
      <Container></Container>
    </Navbar>
  );
};

export default Header;
