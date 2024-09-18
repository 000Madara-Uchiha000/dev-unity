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
    document.querySelector(".container").innerHTML = "YOU MUST BE LOGIN TO SEE All EVENTS"
    document.querySelector(".container").style.textAlign = "center"
    document.querySelector(".sliding-object").style.display = "none"
    document.querySelector(".line").style.display = "none"
  }
}).then((res)=>{
  nn()
})
} 
function nn() {
  let s;
  let loc = localStorage.getItem("token"); 
  axios({
    url: "http://localhost:8080/api/check/role/super",
    method: "GET",
    headers: {
    "Content-Type": "application/json",
    Authorization: `${loc}`,
    },
  }).catch((res)=>{
  console.log(res.response.status)
  if(res.response.status == 401){
    document.querySelector(".add").style.display = "none"
  }
  }).then((res)=>{
    document.querySelector(".add").style.display = "block"
  })
  
    axios({
        url: "http://localhost:8080/api/event/user/all",
        method: "GET",
        headers: {
        "Content-Type": "application/json",
        Authorization: `${loc}`,
        },
  }).then((res) => {
    s = Object.values(res)[0]["data"];
    what(s);
  });

  function what(s) {
  document.querySelector(".add").addEventListener("click", function(){
    window.location = "add_ev.html"
  })
    let cont = document.querySelector(".container");
    console.log(s[0].title);
    let pos = "left";
    let hov = "hover";
    let sty = `style="right :0;"`;
    for (let i = 0; i < s.length; i++) {
      if (i % 2 == 0) {
        pos = "left";
        hov = "hover";
        sty = "";
      } else {
        pos = "right";
        hov = "hoverr";
        sty = `style="right: 0;"`;
      }
      cont.innerHTML += `
        <div class="evv">
                    <a class="hover" ${sty}></a>
                    <div class="event-${pos}">
                        <div class="event-img">
                            <div class="zoom">
                                <img src="data:image/jpeg;base64,${s[i].photo}" alt=""></div>
                        </div>
                        <div class="event-desc">
                            <p class="desc">${s[i].title}</p>
                        </div>
                        <div class="${s[i].id}" id = "sas" >
                    </div>
                </div>
                `;
    }
    
    document.querySelectorAll(".hover").forEach((el) => {
      el.addEventListener("click", function () {
        let img = el.parentElement.querySelector("img").getAttribute("src");
        img = img.substr(0, img.length - 3) + "jpg";
        sessionStorage.setItem(
          "name",
          el.parentElement.querySelector(".desc").innerHTML
        );
        sessionStorage.setItem(
          "desc",
          el.parentElement.querySelector(".desc").innerHTML
        );
        sessionStorage.setItem("img", img);
        sessionStorage.setItem("id", el.parentElement.querySelector("#sas").classList.value)
        window.location.href = "event.html";
      });
    });
    document.addEventListener("scroll", () => {
      if (
        Math.floor(
          (window.scrollY / document.querySelector(".content").offsetHeight) *
            100
        ) < 100
      ) {
        const nada =
          Math.floor(
            (window.scrollY / document.querySelector(".content").offsetHeight) *
              100
          ) + 1;
        document.querySelector(".fill").style.height = nada + "%";
        let events = document.querySelectorAll(".evv");
        for (let i = 0; i < Math.floor(nada / 9) + 1; i++) {
          events[i].style.opacity = 1;
        }
      }
    });
  }
};
