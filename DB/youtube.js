const iframeProps = {
    id: "ytplayer",
    type: "text/html",
    width: "720",
    height: "405",
    src: "https://www.youtube.com/embed/cgdne04i99I",
    frameborder: "0",
    allowfullscreen: "allowfullscreen",
  };
  export default function Main() {
    return (
      <div>
        <iframe {...iframeProps}></iframe>
      </div>
    );
  }