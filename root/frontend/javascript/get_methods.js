let registered_users = document.getElementById('odamchala');
let devunity_member = document.getElementById('devmem');
let events = document.getElementById('events');
let par = document.getElementById('par');
let loc = localStorage.getItem('token')
console.log(loc)
axios({
    url:"http://localhost:8080/api/super/admin/statistics/user/count",
    method: "GET",
    headers : {
        'Content-Type': 'application/json',
        'Authorization': `${loc}`,
    }
}).then(res => {
    let s = Object.values(res)[0]['data'];
    console.log(s);
    registered_users.innerHTML=s
})
axios({
    url:"http://localhost:8080/api/super/admin/statistics/dev/member/count",
    method: "GET",
    headers : {
        'Content-Type': 'application/json',
        'Authorization': `${loc}`,
    }
}).then(res => {
    let f = Object.values(res)[0]['data'];
    console.log(f);
    devunity_member.innerHTML=f
})
axios({
    url:"http://localhost:8080/api/super/admin/statistics/event/count",
    method: "GET",
    headers : {
        'Content-Type': 'application/json',
        'Authorization': `${loc}`,
    }
}).then(res => {
    let d = Object.values(res)[0]['data'];
    console.log(d);
    events.innerHTML=d
})
axios({
    url:"http://localhost:8080/api/super/admin/statistics/event/participant/count",
    method: "GET",
    headers : {
        'Content-Type': 'application/json',
        'Authorization': `${loc}`,
    }
}).then(res => {
    let l = Object.values(res)[0]['data'];
    console.log(l);
    par.innerHTML=l
})