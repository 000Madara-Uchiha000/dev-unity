// import confetti from 'canvas-confetti';
function find(a) {
  let row = Math.sqrt(a);
  row = Math.floor(row);
  let avg = Math.floor(a / row);
  return [row, avg];
}
function mll(ms) {
  // Calculate total seconds
  const totalSeconds = Math.floor(ms / 1000);

  // Calculate hours, minutes, and seconds
  const hours = Math.floor(totalSeconds / 3600);
  const minutes = Math.floor((totalSeconds % 3600) / 60);
  const seconds = totalSeconds % 60;

  // Format the hours, minutes, and seconds to "hh:mm:ss"
  const formattedHours = String(hours).padStart(2, "0");
  const formattedMinutes = String(minutes).padStart(2, "0");
  const formattedSeconds = String(seconds).padStart(2, "0");

  return `${formattedHours}:${formattedMinutes}:${formattedSeconds}`;
}
function fnd(arr, el) {
  let c = false;
  arr.forEach((gg) => {
    if (gg.indexOf(el) != -1) {
      c = true;
    }
  });
  if (c) return true;
  else {
    return false;
  }
}
window.onload = function () {
  let img = sessionStorage.getItem("img");
  let id = sessionStorage.getItem("id");
  let loc = localStorage.getItem("token");
  let seat = 0;
  axios({
    url: `http://localhost:8080/api/event/user?id=${id}`,
    method: "GET",
    headers: {
      "Content-Type": "application/json",
      Authorization: `${loc}`,
    },
  }).then((res) => {
    s = Object.values(res)[0]["data"];
    console.log(s);
    seat = s.participation_limit;
    wtf(s);
  });
  function wtf(s) {
    console.log(img);
    document
      .querySelector(".eimg")
      .setAttribute("src", "data:image/jpeg;base64," + s.photo);
    if (img == "img/ev10.jpg") {
      document.querySelector(".eimg").style.transform = "translate(0,-10%)";
    }
    document.querySelector(".ename").innerHTML = s.title;
    document.querySelector(".edesc").innerHTML = s.body;
    document.querySelector(".snum").innerHTML = s.availability;
    let row = s.teamNumber / 2;
    let num = s.teamSize;

    document.querySelectorAll(".half").forEach((el) => {
      for (let i = 0; i < row; i++) {
        el.innerHTML += `<div class="row"></div>`;
      }
      el.querySelectorAll(".row").forEach((row) => {
        for (let j = 0; j < num; j++) {
          row.innerHTML += `<div class="seat"></div>`;
        }
      });
      el.innerHTML += `<div class="row"></div>`;
      for (let j = 0; j < seat / 2 - row * num; j++) {
        el.querySelectorAll(".row")[
          el.querySelectorAll(".row").length - 1
        ].innerHTML += `<div class="seat"></div>`;
      }
    });
    if (seat / 2 > row * num) {
      row++;
    }
    document.querySelectorAll(".row").forEach((el) => {
      el.style.height = 100 / row + "%";
    });

    document.querySelectorAll(".seat").forEach((el) => {
      el.style.width = 100 / num + "%";
    });
    let seats = document.querySelectorAll(".seat");
    for (let i = s.availability * s.teamSize; i < seats.length; i++) {
      seats[i].style.backgroundColor = "grey";
    }
    document.addEventListener("scroll", function () {
      if (window.scrollY >= 800) {
        document.querySelector("#c").style.height = "30%";
        setTimeout(function () {
          document.querySelector("#c > .place").style.display = "block";
        }, 1000);
        setTimeout(function () {
          document.querySelector("#b").style.height = "45%";
          setTimeout(function () {
            document.querySelector("#b > .place").style.display = "block";
          }, 1000);
        }, 500);
        setTimeout(function () {
          document.querySelector("#a").style.height = "60%";
          setTimeout(function () {
            document.querySelector("#a > .place").style.display = "block";
          }, 1000);
        }, 1000);
      }
    });

    // document.querySelectorAll(".podium__bar").forEach(el=>{
    //   el.querySelector(".place").innerHTML = s.prizeDtos[0].name
    //   el.querySelector(".prize-img").setAttribute("src", "data:image/jpeg;base64,"+s.prizeDtos[0].photo)
    // })

    console.log(s.prizeDtos.length);
    const nums = ["a", "b", "c"];
    for (let i = 0; i < s.prizeDtos.length; i++) {
      let bar = document.querySelector("#" + nums[i]);
      bar.querySelector(".place").innerHTML = s.prizeDtos[i].name;
      bar
        .querySelector(".prize-img")
        .setAttribute("src", "data:image/jpeg;base64," + s.prizeDtos[i].photo);
      console.log(s.prizeDtos[i].name);
    }
    let dd = new Date(s.lastRegisterTime);
    let d = new Date();
    document.querySelector("#cnt").innerHTML = mll(dd - d);
    setInterval(function () {
      let d = new Date();
      document.querySelector("#cnt").innerHTML = mll(dd - d);
    }, 1000);
    let names = [];
    let used = [];
    let chs = false;
    axios({
      url: `http://localhost:8080/api/event/user/classmates`,
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: `${loc}`,
      },
    }).then((res) => {
      s = Object.values(res)[0]["data"];
      console.log(s);
      s.forEach((el) => {
        names.push(`${el.lastName} ${el.firstName}`);
        document.querySelector("#cms").innerHTML += `
      <li class="list-group-item">${el.lastName} ${el.firstName}  </li>
      `;
      });
      document.querySelectorAll(".list-group-item").forEach((el) => {
        console.log(el);
        el.addEventListener("click", function () {
          chs = true;
          inp.value = el.innerHTML;
          el.style.backgroundColor = "skyblue";
          setTimeout(function () {
            el.style.backgroundColor = "white";
          }, 50);
        });
      });
    });

    console.log(names);
    for (let i = 0; i < s.teamSize * 1 - 1; i++) {
      document.querySelector(".cms").innerHTML += `
    <div class="cm">
      <p>${i + 1}-classmate</p>
      <input type="text" class="search" data-bs-toggle="modal" data-bs-target="#exampleModal" readonly>
    </div>
    `;
    }
    let current = 0;
    document.querySelectorAll(".cm").forEach((el) => {
      el.addEventListener("click", function () {
        current = el.querySelector("p").innerHTML.substring(0, 1) * 1;
        console.log(current);
      });
    });
    let inp = document.querySelector("#sear");
    inp.addEventListener("input", function () {
      chs = false;
      document.querySelector("#cms").innerHTML = "";
      names.forEach((el) => {
        if (
          el.toLowerCase().indexOf(inp.value.toLowerCase()) != -1 &&
          used.indexOf(el) == -1
        ) {
          document.querySelector(
            "#cms"
          ).innerHTML += `<li class="list-group-item">${el}  </li>`;
        }
      });
      document.querySelectorAll(".list-group-item").forEach((el) => {
        chs = true;
        console.log(el);
        el.addEventListener("click", function () {
          inp.value = el.innerHTML;
          el.style.backgroundColor = "skyblue";
          setTimeout(function () {
            el.style.backgroundColor = "white";
          }, 50);
        });
      });
    });
    document.querySelector("#save").addEventListener("click", function () {
      if (chs) {
        used = [];
        let inps = document.querySelectorAll(".search");
        inps[current - 1].value = inp.value;
        inp.value = "";
        document.querySelector("#cms").innerHTML = "";
        inps.forEach((el) => {
          used.push(el.value);
        });
        names.forEach((el) => {
          if (!fnd(used, el)) {
            document.querySelector(
              "#cms"
            ).innerHTML += `<li class="list-group-item">${el}  </li>`;
          }
        });
        document.querySelectorAll(".list-group-item").forEach((el) => {
          console.log(el);
          el.addEventListener("click", function () {
            chs = true;
            inp.value = el.innerHTML;
            el.style.backgroundColor = "skyblue";
            setTimeout(function () {
              el.style.backgroundColor = "white";
            }, 50);
          });
        });
      } else {
        alert("choose classmate");
      }
    });
  }
  document.querySelector(".reg").addEventListener("click", function () {
    let loc = localStorage.getItem("token");
    let teamName = document.getElementById("teamName");
    let obj = {
      teamName: teamName,
      teamMemberIds: password,
    };
    console.log(obj);
    axios({
      url: "http://localhost:8080/api/auth/register",
      method: "post",
      data: obj,
    }).then((res) => {
      localStorage.setItem("token", res.data);

      window.location.href = "index.html";
    });
    var end = Date.now() + 1 * 1000;

    var colors = ["#A020F0", "#191970"]; // Buckeyes colors

    (function frame() {
      colors.forEach((color) => {
        // Launch confetti for each color
        confetti({
          particleCount: 1, // 3 particles for each color
          angle: 60,
          spread: 55,
          origin: { x: 0 },
          colors: [color], // Ensure each burst uses a specific color
        });
        confetti({
          particleCount: 1, // 3 particles for each color
          angle: 120,
          spread: 55,
          origin: { x: 1 },
          colors: [color], // Ensure each burst uses a specific color
        });
      });

      if (Date.now() < end) {
        requestAnimationFrame(frame);
      }
    })();
  });
};
