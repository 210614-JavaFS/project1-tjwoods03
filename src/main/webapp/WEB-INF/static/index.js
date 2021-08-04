const URL = 'http://localhost:8080/project1/'

let signUpButton = document.getElementById("signUpButton")
let loginButton = document.getElementById("loginButton")

signUpButton.onclick = signUp
loginButton.onclick = login;

function getUserInfo(newUsername, newPassword, newFirstName = "", newLastName = "", newEmail = ""){
    let credentials = {
        userName:newUsername,
        pass:newPassword,
        firstName:newFirstName,
        lastName:newLastName,
        email:newEmail,
        userRoleID:0,
        userRole:""
    }
    return credentials
}

async function signUp(){
    event.preventDefault();

    let newFirstName = document.getElementById("newFirstName").value
    let newLastName = document.getElementById("newLastName").value
    let newEmail = document.getElementById("newEmail").value
    let newUsername = document.getElementById("newUsername").value
    let newPassword = document.getElementById("newPassword").value
    let info = getUserInfo(newUsername, newPassword, newFirstName, newLastName, newEmail )
    let response = await fetch(URL + 'signUp', {
        method:'POST',
        body:JSON.stringify(info)
    })
    if(response.status===201){
        deleteSignUpForm()
        employeeService()
    }else{
        console.log("New user was unsuccessfully created.")
    }
}

async function login(){
  event.preventDefault();

  let username = document.getElementById("LoginUsername").value;
  let password = document.getElementById("LoginPassword").value;
  let cred = getUserInfo(username, password);
  let response = await fetch(URL + 'login', {
    method:'POST',
    body:JSON.stringify(cred)
  });
  if (response.status==201){
    deleteSignUpForm();
    let profileInfo = await response.json();
    document.getElementById("registerPrompt").innerHTML = profileInfo.userRoleID + " " + profileInfo.firstName + ", " + "please use the navbar to perform your desired actions.";
    if (profileInfo.userRoleID == "2") {
      managerService();
    } else {
      employeeService();
    }
  } else {
    console.log("User login fail");
  }
}

async function signOut(){
    let response = await fetch(URL + 'signOut', {
        method:'POST'
    })
    window.location.replace('http://localhost:8080/static/index.html')
}

async function employeeService(){
    let navbarService = document.getElementById("servicesPlaceHolder");

  let managerServTag = document.createElement("a");
  managerServTag.setAttribute("data-toggle", "dropdown");
  managerServTag.setAttribute("class", "nav-item nav-link dropdown-toggle");
  managerServTag.innerHTML = "Services";
  navbarService.appendChild(managerServTag);

  let managerServList = document.createElement("div");
  managerServList.setAttribute("class", "dropdown-menu");

  let managerServList1 = document.createElement("a");
  managerServList1.setAttribute("class", "dropdown-item");
  managerServList1.innerHTML = "View Past Tickets";
  managerServList1.setAttribute("id", "viewPastTickets");

  let managerServList2 = document.createElement("a");
  managerServList2.setAttribute("class", "dropdown-item");
  managerServList2.setAttribute("id", "viewPendingRequest");
  managerServList2.innerHTML = "View Pending Request";

  let managerServList3 = document.createElement("a");
  managerServList3.setAttribute("class", "dropdown-item");
  managerServList3.setAttribute("id", "submitNewRequest");
  managerServList3.innerHTML = "Submit New Request";

  managerServList.appendChild(managerServList1);
  managerServList.appendChild(managerServList2);
  managerServList.appendChild(managerServList3);
  navbarService.appendChild(managerServList);

  let viewPastTicketsButton = document.getElementById("viewPastTickets");
  viewPastTicketsButton.onclick = viewPastTickets;
  let viewPendingRequestButton = document.getElementById("viewPendingRequest");
  viewPendingRequestButton.onclick = viewPendingRequest;
  let submitNewRequestButton = document.getElementById("submitNewRequest");
  submitNewRequestButton.onclick = submitNewRequest;
}

async function managerService(){
    let navbarService = document.getElementById("servicesPlaceHolder");

  let managerServTag = document.createElement("a");
  managerServTag.setAttribute("data-toggle", "dropdown");
  managerServTag.setAttribute("class", "nav-item nav-link dropdown-toggle");
  managerServTag.innerHTML = "Services";
  navbarService.appendChild(managerServTag);

  let managerServList = document.createElement("div");
  managerServList.setAttribute("class", "dropdown-menu");
  let managerServList1 = document.createElement("button");
  managerServList1.setAttribute("class", "dropdown-item");
  managerServList1.setAttribute("id", "viewAllRequest");
  managerServList1.innerHTML = "View All Request";

  let managerServList2 = document.createElement("button");
  managerServList2.setAttribute("class", "dropdown-item");
  managerServList2.setAttribute("id", "processRequest");
  managerServList2.innerHTML = "Process Request";

  managerServList.appendChild(managerServList1);
  managerServList.appendChild(managerServList2);
  navbarService.appendChild(managerServList);

  let viewAllRequestButton = document.getElementById("viewAllRequest");
  viewAllRequestButton.onclick = viewAllRequestFunc;
  let processRequestButton = document.getElementById("processRequest");
  processRequestButton.onclick = processRequest;
}

function getReimb(){
    let storeAmount = Math.trunc(document.getElementById("input_amount").value * 100);

  let reimb_model = {
    reimbusermentID:0,
    reimbursementAmount:storeAmount,
    reimbursementSubmitted:"",
    reimbursementResolved:"",
    reimbursementDescription:document.getElementById("input_description").value,
    reimbursementReceipt:false,
    reimbursementAuthor:0,
    reimbursementResolver:0,
    reimbursementStatusID:0,
    reimbursementTypeID:document.getElementById("input_reimb_type").value
  }

  return reimb_model;
}

async function getAllReimb(){
    let response = await fetch(URL + 'reimb')

    if(response.status===201){
        let data = await response.json()
        populateReimbTable(data)
    }else{
        console.log('Something went wrong')
    }
}

async function printTable(urlAttribute){
    let response = await fetch(URL + urlAttribute, {
        method:'POST'
      });
    
      if (response.status==201){
        let pageFrontContainer = document.getElementById("pageFrontContainer");
        pageFrontContainer.innerHTML = "<br><h2>Past Tickets</h2><br><br>";
    
        let tableContainer = document.createElement("div");
        tableContainer.setAttribute("class", "table-responsive");
        let tableAllRequest = document.createElement("table");
        tableAllRequest.setAttribute("class", "table table-striped table-sm");
        let thead = document.createElement("thead");
        let tr = document.createElement("tr");
        
        let th = document.createElement("th");
        th.setAttribute("scope","col");
        th.innerText = "Reimb ID";
        tr.appendChild(th);
    
        th = document.createElement("th");
        th.setAttribute("scope","col");
        th.innerText = "Reimb Amount";
        tr.appendChild(th);
    
        th = document.createElement("th");
        th.setAttribute("scope","col");
        th.innerText = "Submit Time";
        tr.appendChild(th);
    
        th = document.createElement("th");
        th.setAttribute("scope","col");
        th.innerText = "Resolved Time";
        tr.appendChild(th);
    
        th = document.createElement("th");
        th.setAttribute("scope","col");
        th.innerText = "Description";
        tr.appendChild(th);
    
        th = document.createElement("th");
        th.setAttribute("scope","col");
        th.innerText = "Author";
        tr.appendChild(th);
    
        th = document.createElement("th");
        th.setAttribute("scope","col");
        th.innerText = "Resolver";
        tr.appendChild(th);
    
        th = document.createElement("th");
        th.setAttribute("scope","col");
        th.innerText = "Status";
        tr.appendChild(th);
    
        th = document.createElement("th");
        th.setAttribute("scope","col");
        th.innerText = "Type";
        tr.appendChild(th);
    
        thead.appendChild(tr);
        tableAllRequest.appendChild(thead);
    
        let tbody = document.createElement("tbody");
        tr = document.createElement("tr");
        let td = document.createElement("td");
    
        let reimbGroup = await response.json();
        
        for (let reimbIndex in reimbGroup) {
          td.innerText = reimbGroup[reimbIndex].reimbursementID;
          tr.appendChild(td);
          td = document.createElement("td");
          td.innerText = reimbGroup[reimbIndex].reimbursementAmount / 100;
          tr.appendChild(td);
          td = document.createElement("td");
          td.innerText = reimbGroup[reimbIndex].reimbursementSubmitted;
          tr.appendChild(td);
          td = document.createElement("td");
          td.innerText = reimbGroup[reimbIndex].reimbursementResolved;
          tr.appendChild(td);
          td = document.createElement("td");
          td.innerText = reimbGroup[reimbIndex].reimbursementDescription;
          tr.appendChild(td);
          td = document.createElement("td");
          td.innerText = reimbGroup[reimbIndex].reimbursementAuthor;
          tr.appendChild(td);
          td = document.createElement("td");
          td.innerText = reimbGroup[reimbIndex].reimbursementResolver;
          tr.appendChild(td);
          td = document.createElement("td");
          td.innerText = reimbGroup[reimbIndex].reimbursementStatusID;
          tr.appendChild(td);
          td = document.createElement("td");
          td.innerText = reimbGroup[reimbIndex].reimbursementTypeID;
          tr.appendChild(td);
          td = document.createElement("td");
    
          tbody.appendChild(tr);
          tr = document.createElement("tr");
        }
    
        tableAllRequest.appendChild(tbody);
        tableContainer.appendChild(tableAllRequest);
        pageFrontContainer.appendChild(tableContainer);
      } else {
        console.log("Get User Requests Fail");
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
    let response = await fetch(URL + 'employee/submit/type', {
        method:'POST'
      });
    
      if (response.status==201){
        let reimbTypeGroup = await response.json();
    
        let pageFrontContainer = document.getElementById("pageFrontContainer");
        pageFrontContainer.innerHTML = "<br><h2>Create Request</h2><br><br>";
        let requestForm = document.createElement("form");
        requestForm.setAttribute("method", "post");
      
        let formGroup1 = document.createElement("div");
        formGroup1.setAttribute("class", "form-group col-xs-6");
        let formGroup1Label = document.createElement("label");
        formGroup1Label.setAttribute("for", "REIMB_AMOUNT");
        let formGroup1LabelH = document.createElement("h4");
        formGroup1LabelH.innerText = "Reimbursment Amount";
        let formGroup1Input = document.createElement("input");
        formGroup1Input.setAttribute("type", "text");
        formGroup1Input.setAttribute("class", "form-control");
        formGroup1Input.setAttribute("name", "input_amount");
        formGroup1Input.setAttribute("id", "input_amount");
        formGroup1Input.setAttribute("placeholder", "999.99");
        formGroup1Input.setAttribute("title", "enter your desired amount to reimburse");
        formGroup1Label.appendChild(formGroup1LabelH);
        formGroup1.appendChild(formGroup1Label);
        formGroup1.appendChild(formGroup1Input);
        requestForm.appendChild(formGroup1);
    
        let formGroup2 = document.createElement("div");
        formGroup2.setAttribute("class", "form-group col-xs-6");
        let formGroup2Label = document.createElement("label");
        formGroup2Label.setAttribute("for", "REIMB_DESCRIPTION");
        let formGroup2LabelH = document.createElement("h4");
        formGroup2LabelH.innerText = "Reimbursment Description";
        let formGroup2Input = document.createElement("input");
        formGroup2Input.setAttribute("type", "text");
        formGroup2Input.setAttribute("class", "form-control");
        formGroup2Input.setAttribute("name", "input_description");
        formGroup2Input.setAttribute("id", "input_description");
        formGroup2Input.setAttribute("placeholder", "Short description for reimbursement.");
        formGroup2Input.setAttribute("title", "enter the reason you requesting reimbursement");
        formGroup2Label.appendChild(formGroup2LabelH);
        formGroup2.appendChild(formGroup2Label);
        formGroup2.appendChild(formGroup2Input);
        requestForm.appendChild(formGroup2);
    
        let formGroup3 = document.createElement("div");
        formGroup3.setAttribute("class", "form-group col-xs-6");
        let formGroup3Label = document.createElement("label");
        formGroup3Label.setAttribute("for", "REIMB_TYPE");
        let formGroup3LabelH = document.createElement("h4");
        formGroup3LabelH.innerText = "Reimbursment Type";
    
        let formGroup3Select = document.createElement("select");
        formGroup3Select.setAttribute("class", "form-control");
        formGroup3Select.setAttribute("name", "input_reimb_type");
        formGroup3Select.setAttribute("id", "input_reimb_type");
    
        let formGroup3SelectOption1 = document.createElement("option");
        formGroup3SelectOption1.setAttribute("selected", true);
        formGroup3SelectOption1.innerHTML = "Make Selection";
        formGroup3Select.appendChild(formGroup3SelectOption1);
    
        for (let reimbTypeIndex in reimbTypeGroup) {
          formGroup3Select.appendChild(appendSelectOption(reimbTypeGroup[reimbTypeIndex].reimbursementTypeID, reimbTypeGroup[reimbTypeIndex].reimb_type));//might be something here
        }
    
        formGroup3Label.appendChild(formGroup3LabelH);
        formGroup3.appendChild(formGroup3Label);
        formGroup3.appendChild(formGroup3Select);
        requestForm.appendChild(formGroup3);
    
        let formGroup4 = document.createElement("div");
        formGroup4.setAttribute("class", "form-group col-xs-12");
        formGroup4.innerHTML = "<br>";
        let formGroup4Submit = document.createElement("button");
        formGroup4Submit.setAttribute("class", "btn btn-lg btn-success");
        formGroup4Submit.setAttribute("id", "createRequestButton");
        formGroup4Submit.setAttribute("type", "submit");
        formGroup4Submit.innerText = "Create";  
        let formGroup4Reset = document.createElement("button");
        formGroup4Reset.setAttribute("class", "btn btn-lg");  
        formGroup4Reset.setAttribute("type", "reset");
        formGroup4Reset.innerHTML = "Reset";
        let formGroup4ResetI = document.createElement("i");
        formGroup4ResetI.setAttribute("class", "glyphicon glyphicon-repeat")
        formGroup4Reset.appendChild(formGroup4ResetI);
        formGroup4.appendChild(formGroup4Submit);
        formGroup4.appendChild(formGroup4Reset);
        requestForm.appendChild(formGroup4);
    
        pageFrontContainer.appendChild(requestForm);
    
        let createRequestButton = document.getElementById("createRequestButton");
        createRequestButton.onclick = submitRequest;
      } else {
        console.log("Connect to server fail.");

        function appendSelectOption(optionvalue, optionPrompt) {
            let SelectOption = document.createElement("option");
            SelectOption.setAttribute("value", optionvalue);
            SelectOption.innerHTML = optionPrompt;
            return SelectOption;
         }

     }
}



async function processRequest(){
    let response = await fetch(URL + 'manager/pending', {
        method:'POST'
      });
      if (response.status==201){
        let pageFrontContainer = document.getElementById("pageFrontContainer");
        pageFrontContainer.innerHTML = "<br><h2>Pending Request</h2><br><br>";
    
        let tableContainer = document.createElement("div");
        tableContainer.setAttribute("class", "table-responsive");
        let tableAllRequest = document.createElement("table");
        tableAllRequest.setAttribute("class", "table table-striped table-sm");
        let thead = document.createElement("thead");
        let tr = document.createElement("tr");
        
        let th = document.createElement("th");
        th.setAttribute("scope","col");
        th.innerText = "Reimb ID";
        tr.appendChild(th);
    
        th = document.createElement("th");
        th.setAttribute("scope","col");
        th.innerText = "Reimb Amount";
        tr.appendChild(th);
    
        th = document.createElement("th");
        th.setAttribute("scope","col");
        th.innerText = "Submit Time";
        tr.appendChild(th);
    
        th = document.createElement("th");
        th.setAttribute("scope","col");
        th.innerText = "Resolved Time";
        tr.appendChild(th);
    
        th = document.createElement("th");
        th.setAttribute("scope","col");
        th.innerText = "Description";
        tr.appendChild(th);
    
        th = document.createElement("th");
        th.setAttribute("scope","col");
        th.innerText = "Author";
        tr.appendChild(th);
    
        th = document.createElement("th");
        th.setAttribute("scope","col");
        th.innerText = "Resolver";
        tr.appendChild(th);
    
        th = document.createElement("th");
        th.setAttribute("scope","col");
        th.innerText = "Status";
        tr.appendChild(th);
    
        th = document.createElement("th");
        th.setAttribute("scope","col");
        th.innerText = "Type";
        tr.appendChild(th);
        
        th = document.createElement("th");
        th.setAttribute("scope","col");
        th.innerText = "Process";
        tr.appendChild(th);
    
        thead.appendChild(tr);
        tableAllRequest.appendChild(thead);
    
        let tbody = document.createElement("tbody");
        tr = document.createElement("tr");
        let td = document.createElement("td");
        let tableCheckbox = document.createElement("input");
    
        let reimbGroup = await response.json();
    
        let i = 0;
    
        for (let reimbIndex in reimbGroup) {
          td.innerText = reimbGroup[reimbIndex].reimbursementID;
          tr.appendChild(td);
          td = document.createElement("td");
          td.innerText = reimbGroup[reimbIndex].reimbursementAmount / 100;
          tr.appendChild(td);
          td = document.createElement("td");
          td.innerText = reimbGroup[reimbIndex].reimbursementSubmitted;
          tr.appendChild(td);
          td = document.createElement("td");
          td.innerText = reimbGroup[reimbIndex].reimbursementResolved;
          tr.appendChild(td);
          td = document.createElement("td");
          td.innerText = reimbGroup[reimbIndex].reimbursementDescription;
          tr.appendChild(td);
          td = document.createElement("td");
          td.innerText = reimbGroup[reimbIndex].reimbursementAuthor;
          tr.appendChild(td);
          td = document.createElement("td");
          td.innerText = reimbGroup[reimbIndex].reimbursementResolver;
          tr.appendChild(td);
          td = document.createElement("td");
          td.innerText = reimbGroup[reimbIndex].reimbursementStatusID;
          tr.appendChild(td);
          td = document.createElement("td");
          td.innerText = reimbGroup[reimbIndex].reimbursementTypeID;
          tr.appendChild(td);
          td = document.createElement("td");
          tableCheckbox.setAttribute("class", "form-check-input");
          tableCheckbox.setAttribute("type", "checkbox");
          tableCheckbox.setAttribute("id", "reimb_checkbox_"+i++);
          tableCheckbox.setAttribute("value", reimbGroup[reimbIndex].reimb_id);
          td.appendChild(tableCheckbox);
          tr.appendChild(td);
          td = document.createElement("td");
          tableCheckbox = document.createElement("input");
    
          tbody.appendChild(tr);
          tr = document.createElement("tr");
        }
    
        let tableProcess = document.createElement("button");
        tableProcess.setAttribute("id", "approveAll");
        tableProcess.setAttribute("type", "submit");
        tableProcess.setAttribute("class", "btn btn-primary");
        tableProcess.innerHTML = "Approve All";
        td.appendChild(tableProcess);
        tr.appendChild(td);
        td = document.createElement("td");
    
        tableProcess = document.createElement("button");
        tableProcess.setAttribute("id", "denyAll");
        tableProcess.setAttribute("type", "submit");
        tableProcess.setAttribute("class", "btn btn-primary");
        tableProcess.innerHTML = "Deny All";
        td.appendChild(tableProcess);
        tr.appendChild(td);
        td = document.createElement("td");
    
        tableProcess = document.createElement("button");
        tableProcess.setAttribute("id", "approve");
        tableProcess.setAttribute("type", "submit");
        tableProcess.setAttribute("class", "btn btn-primary");
        tableProcess.innerHTML = "Approve Checked";
        td.appendChild(tableProcess);
        tr.appendChild(td);
        td = document.createElement("td");
    
        tableProcess = document.createElement("button");
        tableProcess.setAttribute("id", "deny");
        tableProcess.setAttribute("type", "submit");
        tableProcess.setAttribute("class", "btn btn-primary");
        tableProcess.innerHTML = "Deny Checked";
        td.appendChild(tableProcess);
        tr.appendChild(td);
    
        tbody.appendChild(tr);
    
        tableAllRequest.appendChild(tbody);
        tableContainer.appendChild(tableAllRequest);
    
        pageFrontContainer.appendChild(tableContainer);
    
        let tableApproveAllButton = document.getElementById("approveAll");
        tableApproveAllButton.onclick = approveAllRequest;
        let tableDenyAllButton = document.getElementById("denyAll");
        tableDenyAllButton.onclick = denyAllRequest;
        let tableApproveButton = document.getElementById("approve");
        tableApproveButton.onclick = approveRequest;
        let tableDenyButton = document.getElementById("deny");
        tableDenyButton.onclick = denyRequest;
    
      } else {
        console.log("Get User Requests Fail");
      }
}

async function submitRequest(){
    event.preventDefault();
    let response = await fetch(URL + 'employee/submit', {
      method:'POST',
      body:JSON.stringify(getReimb())
    });
  
    if (response.status==201){
      let pageFrontContainer = document.getElementById("pageFrontContainer");
      pageFrontContainer.innerHTML = "<br><h2>Request Submitted !</h2><br><br>";
    } else {
      let pageFrontContainer = document.getElementById("pageFrontContainer");
      pageFrontContainer.innerHTML = "<br><h2>Request Submit Ran Into Some Problem.</h2><br><br>";
    }
}

async function approveRequest(){
    let i = 0;
  let response = null;
  let reimbIdInprocess = document.getElementById("reimb_checkbox_"+i++);
  while (reimbIdInprocess != null) {
    if (reimbIdInprocess.checked) {
      response = await fetch(URL + 'manager/approve/single/' + reimbIdInprocess.getAttribute("value"), {
        method:'POST'
      });
    }
    reimbIdInprocess = document.getElementById("reimb_checkbox_"+i++);
  }
  if (response.status==201){
    processRequest();
  }
}

async function denyRequest(){
    let i = 0;
  let response = null;
  let reimbIdInprocess = document.getElementById("reimb_checkbox_"+i++);
  while (reimbIdInprocess != null) {
    if (reimbIdInprocess.checked) {
      response = await fetch(URL + 'manager/deny/single/' + reimbIdInprocess.getAttribute("value"), {
        method:'POST'
      });
    }
    reimbIdInprocess = document.getElementById("reimb_checkbox_"+i++);
  }
  if (response.status==201){
    processRequest();
  }
}

function deleteSignUpForm(){
    let signUpForm = document.getElementById("signUpForm");
    signUpForm.innerHTML = " ";
  
    signUpForm = document.getElementById("loginNavbarAnchor");
    signUpForm.innerHTML = " ";
  
    let profileImgNavbar = document.createElement("img");
    profileImgNavbar.setAttribute("src", "https://github.com/mdo.png");
    profileImgNavbar.setAttribute("alt", "mdo");
    profileImgNavbar.setAttribute("width", "32");
    profileImgNavbar.setAttribute("height", "32");
    profileImgNavbar.setAttribute("class", "rounded-circle");
  
    signUpForm.appendChild(profileImgNavbar);
  
    signUpForm = document.getElementById("loginNavbarMenu");
    signUpForm.style.width = "100px";
    signUpForm.innerHTML = " ";
  
    let profileList1 = document.createElement("button");
    profileList1.setAttribute("class", "dropdown-item");
    profileList1.setAttribute("id", "profile");
    profileList1.innerHTML = "Profile";
    signUpForm.appendChild(profileList1);
  
    let profileList2 = document.createElement("hr");
    profileList2.setAttribute("class", "dropdown-divider");
    signUpForm.appendChild(profileList2);
  
    let profileList3 = document.createElement("button");
    profileList3.setAttribute("class", "dropdown-item");
    profileList3.setAttribute("id", "signOut");
    profileList3.innerHTML = "Sign out";
    signUpForm.appendChild(profileList3);
  
    let profileButton = document.getElementById("profile");
    profileButton.onclick = profile;
    let signOutButton = document.getElementById("signOut");
    signOutButton.onclick = signOut;
}
