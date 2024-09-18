window.onload = function(){
    document.querySelector(".logo").classList.add("active")
    setTimeout(function(){
        document.querySelector(".logo").style.boxShadow = "0px 0px white"
        document.querySelector(".block").style.top = "1000px";
        let leads = document.querySelectorAll(".lead")
        let imgs = document.querySelectorAll(".lead-img>img")
        setTimeout(function(){
            leads[0].classList.add("active");
            leads[1].classList.add("active");
            leads[2].classList.add("active");
            imgs[0].classList.add("active");
            imgs[1].classList.add("active");
            imgs[2].classList.add("active");
            leads[3].classList.add("active");
            imgs[3].classList.add("active");
        },500)
        setTimeout(function(){
            imgs[0].style.boxShadow = "0px 0px white";
            imgs[1].style.boxShadow = "0px 0px white";
            imgs[2].style.boxShadow = "0px 0px white";
            imgs[3].style.boxShadow = "0px 0px white";
        },1000)
        setTimeout(function(){
        },1500)
    },1000)
    
}