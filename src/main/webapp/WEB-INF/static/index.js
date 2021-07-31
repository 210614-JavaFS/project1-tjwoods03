const URL = 'http://localhost:8080/project1/'

//let loginButton = document.getElementById("btnSubmit")
let viewPrevReimb = document.getElementById("viewPrevious")
let submitReimbButton = document.getElementById("submitReimbursement")
let viewPendingReimb = document.getElementById("viewPending")
let viewFilterReimb = document.getElementById("view-filtered")

//loginButton.onclick = login;
viewPrevReimb.onclick = getAllReimb;
submitReimbButton.onclick = submitReimb;
viewPendingReimb.onclick = getPendingReimb;
viewFilterReimb.onclick = getFilteredReimb;

// async function login(){
//     let response = await fetch(URL +'login')

//     if(response.status===200){
//         let data = await response.json()
        
//     }else{
//         console.log('Something went wrong')
//     }
// }



async function getAllReimb(){
    let response = await fetch(URL + 'reimb')

    if(response.status===201){
        let data = await response.json()
        populateReimbTable(data)
    }else{
        console.log('Something went wrong')
    }
}

function populateReimbTable(data){
    let tbody = document.getElementById("prevBody")

    tbody.innerHTML = ""

    for(let reimb of data){
        let row = document.createElement("tr")

        for(let cell in reimb){
            let td = document.createElement("td")
            if(cell=='reimb'&&reimb[cell]){
                td.innerText = reimb[cell].reimbID + ": "+ reimb[cell].reimbAmount + " " + reimb[cell].submitDate + " " + reimb[cell].resolveDate + " " + reimb[cell].reimbDescript + " " 
                + reimb[cell].reimbAuthor + " " + reimb[cell].reimbResolver + " " + reimb[cell].reimbStatus + " " + reimb[cell].reimbType
            }else{
                td.innerText = reimb[cell];
            }
            row.appendChild(td)
        }
        tbody.appendChild(row)
    }
}

async function getPendingReimb(){
    let response = await fetch(URL + 'pendingReimb')

    if(response.status===201){
        let reimbData = await response.json()
        populateReimbTable(reimbData)
    }else{
        console.log("There was a problem accessing reimbursements")
    }
}

function getUserReimnb(){
    let reimbUser = document.getElementById("reimb-username").value
    let reimbAmount = document.getElementById("amount").value
    let reimbType = document.getElementById("type").value
    let reimbDescription = document.getElementById("description").value
    
    let reimb = {
        reimbursementAuthor:reimbUser,
        reimbursementAmount:reimbAmount,
        reimbursementTypeID:reimbType,
        reimbursementDescription:reimbDescription
    }
    return reimb
}

async function submitReimb(){
    let reimb = getUserReimnb()

    let response = await fetch(URL+'reimb',{
        method:'POST',
        body:JSON.stringify(reimb)
    })

    if(response.status===201){
        console.log("Reimbursement successfully submitted")
    }else{
        console.log("Something went wrong trying to submit the reimbursement")
    }
}

async function getFilteredReimb(){
    let response = await fetch(URL + 'filteredReimb')

    if(response.status===200){
        let reimbData = await response.json()
        populateReimbTable(reimbData)
    }else{
        console.log("There was a problem accessing reimbursements")
    }
}