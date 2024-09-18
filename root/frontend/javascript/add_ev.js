window.onload = function () {
  let comp = false;
  document.querySelector(".comp").addEventListener("click", function () {
    if (comp) {
      comp = false;
      document.querySelector(".tp").style.height = "0";
      document.querySelector(".prizes").style.height = "0";
      document.querySelector(".prizes").style.padding = "0";
      document.querySelector(".prizes").style.border = "none";
      document.querySelector(".num").style.height = "0";
      document.querySelector(".numm").style.height = "fit-content";
      document.querySelector(".numb").style.height = "0";
    } else {
      comp = true;
      document.querySelector(".tp").style.height = "fit-content";
      document.querySelector(".numm").style.height = "0";
      document.querySelector(".prizes").style.height = "fit-content";
      document.querySelector(".prizes").style.padding = "3%";
      document.querySelector(".prizes").style.border = "solid 5px transparent";
      if (document.querySelector(".team").checked)
        document.querySelector(".num").style.height = "fit-content";
      if (document.querySelector(".solo").checked)
        document.querySelector(".numb").style.height = "fit-content";
    }
  });
  document.querySelector(".team").addEventListener("click", function () {
    document.querySelector(".numb").style.height = "0";
    document.querySelector(".num").style.height = "fit-content";
    document.querySelector(".solo").checked = false;
  });
  document.querySelector(".solo").addEventListener("click", function () {
    document.querySelector(".num").style.height = "0";
    document.querySelector(".numb").style.height = "fit-content";
    document.querySelector(".team").checked = false;
  });
  let node = document.querySelector(".prize");
  document.querySelectorAll(".delete").forEach((el) => {
    el.addEventListener("click", function () {
      console.log(el.parentElement);
      el.parentElement.remove();
    });
  });
  document.querySelector(".addbtn").addEventListener("click", function () {
    document
      .querySelector(".prizes")
      .insertBefore(node.cloneNode(true), document.querySelector(".addbtn"));
    document.querySelectorAll(".delete").forEach((el) => {
      el.addEventListener("click", function () {
        console.log(el.parentElement);
        el.parentElement.remove();
      });
    });
  });
};

document.getElementById("btn_submit").addEventListener("click", function (event) {
    event.preventDefault();
    const name = document.getElementById("name").value;
    const desc = document.getElementById("desc").value;
    const event_image = document.getElementById("ev_image").files[0];
    const comp = document.getElementById("comp").value;
    const solo = document.getElementById("solo").value;
    const team = document.getElementById("team").value;
    const team_size = document.getElementById("num_teams").value;
    const team_number = document.getElementById("num_seats").value;
    const num_parts = document.getElementById("num-parts").value;

    const num_seats_comp = document.getElementById("num_seats_comp").value;
    const priz_name = document.getElementById("priz_name").value;
    const priz_image = document.getElementById("priz-image").files[0];
    const start_time_date = document.getElementById("start_time_date").value;
    const start_time_time = document.getElementById("start_time_time").value;
    const priz_desc = document.getElementById("priz_desc").value;
    const place = document.getElementById("place").value;
    const combinedDateTime = `${start_time_date}T${start_time_time}`;
    let loc = localStorage.getItem("token");
    console.log(loc);
    const last_reg_time = document.getElementById("last_reg_time").value;
    const last_reg_date = document.getElementById("last_reg_date").value;
    const lastcombinedDateTime = `${last_reg_date}T${last_reg_time}`;
    // let obj = {
    //   title: name,
    //   body: desc,
    //   participationLimit: num_seats,
    //   startTime: time,
    //   place: place,
    //   eventPhoto: or,
    //   prizeRequestDtos: [
    //     {
    //       name: priz_name,
    //       description: priz_desc,
    //       photo: or2,
    //     },
    //   ],
    // };
    let formData = new FormData();
    formData.append("title", name);
    formData.append("body", desc);
    formData.append("teamSize", team_size);
    formData.append("teamNumber", team_number);
    formData.append("startTime", combinedDateTime);
    formData.append("place", place);
    formData.append("eventPhoto", event_image);
    formData.append("lastRegisterTime", lastcombinedDateTime);
    let prizes = document.querySelectorAll(".prize")
    let cnt = prizes.length;
    for (let i = 0; i< cnt; i++){
      formData.append(`prizeRequestDtos[${i}].name`, prizes[i].querySelector("#priz_name").value);
      formData.append(`prizeRequestDtos[${i}].description`, prizes[i].querySelector("#priz_desc").value);
      formData.append(`prizeRequestDtos[${i}].photo`, prizes[i].querySelector("#priz-image").files[0]);
    }

    axios.post('http://localhost:8080/api/admin/event', formData, {
        headers: {
          'Content-Type': 'multipart/form-data',
          Authorization: `${loc}`,
        }
      })
    .then((res) => {
        window.location.href = "events.html";
    })
//     axios({
//       url: "http://localhost:8080/api/admin/event",
//       method: "post",
//       data: obj,
//       headers: {
//         "Content-Type": "application/json",
//         Authorization: `${loc}`,
//       },
//     }).then((res) => {
//       window.location.href = "index.html";
//     });
//     console.log(obj);
  });
