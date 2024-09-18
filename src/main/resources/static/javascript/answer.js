window.onload = function(){
    if (sessionStorage.getItem("name") != null){
        let th = sessionStorage.getItem("th");
        let tx = sessionStorage.getItem("tx");
        let name = sessionStorage.getItem("name");
        document.querySelector(".frm").innerHTML = `
        <p class="heading">Answer the question</p>
                <input type="button" class="name" value="CHOOSE QUESTION">
                <p class="theme"> Name: <span class="th">${name}</span></p>
                <p class="theme">Theme: <span class="th">${th}</span></p>
                <p class="theme">Text: <span class="tx">${tx}</span></p>
                <textarea class="text" placeholder="TEXT:"></textarea>
                <div class="send">
                    <button style="background-color: white; border: 1px solid black; color: black;">SEND</button>
                    <button>SEND</button>
                </div>
        
        `
    }
    document.querySelector(".name").addEventListener("click", function(){
        window.location.href = "questions.html"
    })
}