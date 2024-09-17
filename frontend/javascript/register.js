let classes = document.getElementById('class');
axios({
    url:"http://localhost:8080/api/auth/clazz",
    method: "GET"
}).then(res => {
    let s=`
                        <option disabled selected>Tanlang</option>
    `;
    console.log(res)
    res.data.data.forEach(item => {
        
        s += `
                        <option>${item}</option>
        
        `
    })
    
    // handle success
    classes.innerHTML=s


})

document.getElementById('registerForm').addEventListener('submit', function (event) {
    event.preventDefault();

    const firstName = document.getElementById('firstName').value;
    const lastName = document.getElementById('lastName').value;
    const email = document.getElementById('email').value;
    const className = document.getElementById('class').value;
    const password = document.getElementById('password').value;
    const repeat_password = document.getElementById('repeat-password').value;
    
    if (password === repeat_password) {
        let obj={
            username:email,
            password:password,
            firstName:firstName,
            lastName:lastName,
            className:className
        }
        console.log(obj)
        axios({
            url:"http://localhost:8080/api/auth/register",
            method:"post",
            data:obj
        }).then(res=>{
             localStorage.setItem("token",res.data)
    
             window.location.href="index.html"
        })
    }
    else {
        alert("Passwords do not match");
    }

    

    
});
