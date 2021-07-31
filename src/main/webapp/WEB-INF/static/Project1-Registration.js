const URL = 'http://localhost:8080/project1/'

let submitButton = document.getElementById("submit");

submitButton.onclick = addUser;

function getUserInfo(){
    let firstname = document.getElementById("firstname").value;
    let lastname = document.getElementById("lastname").value;
    let newEmail = document.getElementById("email").value;
    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;

    let user = {
        firstName:firstname,
        lastName:lastname,
        email:newEmail,
        userName:username,
        pass:password
    }

    return user;
}

async function addUser(){
    let user = getUserInfo();

    let response = await fetch(URL + 'login' , {
        method:'POST',
        body:JSON.stringify(user)
    })

    if(response.status===201){
        console.log("User successfully created")
    }else{
        console.log("Something went wrong trying to create the new user")
    }

}