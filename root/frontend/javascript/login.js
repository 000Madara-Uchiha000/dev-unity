window.onload = function(){
    document.getElementById('loginForm').addEventListener('submit', function (event) {
        event.preventDefault();
        const email = document.getElementById('username').value;
        const password = document.getElementById('password').value;
    
        let obj={
            username:email,
            password:password
        }
        axios({
            url:"http://localhost:8080/api/auth/login",
            method:"post",
            data:obj
        }).then(res=>{
            localStorage.setItem("token",Object.values(res)[0]['data'])
            window.location.href ="index.html"
             
        })
    });
}