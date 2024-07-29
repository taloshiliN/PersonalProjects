import Header from "../components/Header";

function About() {
  return (
    <>
      <Header />
      <h1>About Page</h1>
      <h2>About US</h2>
      <pre>
        “At Mipay, we are committed on offering convenient money transfer <br />
        financial services at affordable prices for our customers particularly{" "}
        <br />
        intersecting with the betting industry. We want to build trust with our{" "}
        <br />
        customers and at the same time exposing the brand and services to a{" "}
        <br />
        great deal of audience and attract more clients more to Moola Betting,{" "}
        <br />
        Mipay as well as to our betting partners.”
      </pre>
      <h2>Services we offer</h2>
      <li>
        <ol>1. Online Transfer  Secure online banking platform </ol>2. Mobile
        payment  Transferring money with our mobile app 3. In-Person Transfer 
        Cash to teller setting with a transaction slip being exchanged 4.
        International transfer  The transfer of money cross borders in
        different currencies (International remittance) 5. Crypto currency
        Transfer  Transferring of money through the exchange of crypto
        currencies like Bitcoin or Ethereum
      </li>
    </>
  );
}

export default About;
