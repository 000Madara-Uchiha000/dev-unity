window.onload = function(){
    let en = false;
    let name = "";
    let th = "";
    let tx = "";
    document.querySelectorAll(".card").forEach(el=>{
        el.addEventListener("click", function(){
            en = true;
            name = el.querySelector(".name").innerHTML
            th = el.querySelector(".th").innerHTML
            tx = el.querySelector(".tx").innerHTML
            document.querySelectorAll(".card").forEach(card=>{
                card.style.backgroundColor = "white";
                card.querySelectorAll("p").forEach(text=>{
                    text.style.color = "black";
                })
            })
            el.style.backgroundColor = "blue";
            el.querySelectorAll("p").forEach(text=>{
                text.style.color = "white";
            })
            document.querySelector(".sbmt").style.backgroundColor = "rgb(50, 50, 255)";
        })
    })
    document.querySelector(".sbmt").addEventListener("click", function(){
        if (en){
            sessionStorage.setItem("name", name)
            sessionStorage.setItem("th", th)
            sessionStorage.setItem("tx", tx)
            window.location.href = "answer.html";
        }
    })
}