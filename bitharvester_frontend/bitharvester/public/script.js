const cursorDot = document.querySelector("[data-cursor-dot]");
const cursorOutline = document.querySelector("[data-cursor-outline]");

window.addEventListener("mousemove", function (e) {
  const posX = e.clientX;
  const posY = e.clientY;

  cursorDot.style.left = `${posX}px`;
  cursorDot.style.top = `${posY}px`;

  cursorOutline.animate(
    {
      left: `${posX}px`,
      top: `${posY}px`,
    },
    { duration: 1500, fill: "forwards" }
  );
});

document.addEventListener("mouseover", function (event) {
  if (event.target.tagName === "A" || event.target.tagName === "BUTTON") {
    handleHoverOrClick(event);
  }
});

function handleHoverOrClick(e) {
  cursorOutline.classList.add("grow-outline");
}

document.addEventListener("mouseout", function (event) {
  cursorOutline.classList.remove("grow-outline");
});
