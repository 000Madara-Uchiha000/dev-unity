
const element4 = document.getElementById('huh3');



  element4.addEventListener('mouseover', function() {
    element4.classList.add('animate__animated', 'animate__rubberBand');
  });
element4.addEventListener('mouseout', function() {
    element4.classList.remove('animate__animated', 'animate__rubberBand');
  });

var typed = new Typed(".auto-type", {
  strings: ["Empowering the Future of Technology", "Innovation and Collaboration at its Best", "Building Tomorrow's Solutions Today", "Transforming Ideas into Reality"],
  typeSpeed: 100,
  backSpeed: 50,
  loop: true,
  loopCount: 1,
  showCursor: true, 
})

window.onload = function(){
  let loc = localStorage.getItem("token"); 
  axios({
    url: "http://localhost:8080/api/check/role/user",
    method: "GET",
    headers: {
    "Content-Type": "application/json",
    Authorization: `${loc}`,
    },
}).catch((res)=>{
console.log(res.response.status)
if(res.response.status == 401){
  console.log(res.response);
}
}).then((res)=>{

})
} 