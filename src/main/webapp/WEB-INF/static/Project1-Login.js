const URL = 'http://localhost:8080/project1/'




let button = document.getElementById('btnSubmit')
button.onclick = submitLogin

function loginInfo(){
    let username = document.getElementById("username").value 
    let password = document.getElementById("password").value

    let user = {
        userName:username,
        pass:password
    }

    return user

}
    
async function submitLogin(){
    let user = loginInfo()

    let response = await fetch(URL+'login', {
        method:'POST',
        body:JSON.stringify(user)
    })

    if(response.status===201){
        console.log("Login successful")
        //Map to new page
    }else{

    }
}
