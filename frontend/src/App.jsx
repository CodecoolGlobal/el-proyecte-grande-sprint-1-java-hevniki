

function App() {

  return (
    <>
      <div>Hello</div>
      <button onClick={async () => {
        const res = await fetch("/api");
        const dummy = await res.json();
        console.log(dummy);
      }}>Click me!</button>
    </>
  )
}

export default App;
