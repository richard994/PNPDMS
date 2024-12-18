document.addEventListener("DOMContentLoaded", () => {
  const btn = document.getElementById("startbtn");
  btn.addEventListener("click", function onClick() {
    sessionStorage.setItem("qp", "Create Quoted Price");
    sessionStorage.setItem("curr", "Create Quoted Price");
  });
});