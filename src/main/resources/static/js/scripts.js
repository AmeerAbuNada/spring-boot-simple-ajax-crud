function openModal(modalId) {
  $(`#${modalId}`).modal("show");
}

function closeModal(modalId, clearFormId) {
  if (clearFormId != undefined && clearFormId != null) {
    document.getElementById(clearFormId).reset();
  }
  $(`#${modalId}`).modal("hide");
}

function gatherItemData(ref) {
  return new FormData(ref);
}

function formDataToJson(formData) {
  let object = {};
  formData.forEach((value, key) => (object[key] = value));
  console.log(object);
  return JSON.stringify(object);
}

function toggleButtonDisability(buttonId, disabled) {
  document.getElementById(buttonId).disabled = disabled;
}

function sendCreateItemRequest(ref) {
  toggleButtonDisability("create-employee-btn", true);
  axios
    .post("/employees", gatherItemData(ref))
    .then((response) => {
      toastr.success(response.data.message);
      setTimeout(() => {
        window.location.reload();
      }, 600);
    })
    .catch((error) => {
      toastr.error(error.response.data.message);
      toggleButtonDisability("create-employee-btn", false);
    });
}

let selectedItemForEditing = -1;
async function editItem(id, ref) {
  ref.disabled = true;
  let response = await getItemDataFromApi(id);
  ref.disabled = false;

  if (!response.ok) {
    selectedItemForEditing = -1;
    return toastr.error(response.data.message);
  }

  populateEditFormWithItemsData(response.data.data);
  openModal("edit-employee-modal");
}

async function getItemDataFromApi(id) {
  return await axios
    .get(`/employees/${id}`)
    .then((response) => {
      return {
        ok: true,
        data: response.data,
      };
    })
    .catch((error) => {
      return {
        ok: false,
        data: error.response.data,
      };
    });
}

function populateEditFormWithItemsData(data) {
  selectedItemForEditing = data.id;
  document.getElementById("name-edit").value = data.name;
  document.getElementById("email-edit").value = data.email;
  document.getElementById("birthdate-edit").value = data.parsedBirthdate;
}

function gatherDataForUpdate() {
  return {
    name: document.getElementById("name-edit").value,
    email: document.getElementById("email-edit").value,
    birthdate: document.getElementById("birthdate-edit").value,
  };
}

function sendUpdateItemRequest(ref) {
  toggleButtonDisability("edit-employee-btn", true);
  let data = gatherDataForUpdate();
  console.log(data);
  axios
    .put(`/employees/${selectedItemForEditing}`, data)
    .then((response) => {
      toastr.success(response.data.message);
      setTimeout(() => {
        window.location.reload();
      }, 600);
    })
    .catch((error) => {
      toastr.error(error.response.data.message);
      toggleButtonDisability("edit-employee-btn", false);
    });
}

function confirmDelete(id, ref) {
  Swal.fire({
    title: "Are you sure?",
    text: "You won't be able to revert this!",
    icon: "warning",
    showCancelButton: true,
    confirmButtonColor: "#3085d6",
    cancelButtonColor: "#d33",
    confirmButtonText: "Yes, delete it!",
  }).then((result) => {
    if (result.isConfirmed) {
      deleteItem(id, ref);
    }
  });
}

function deleteItem(id, ref) {
  axios
    .delete(`/employees/${id}`)
    .then((response) => {
      ref.closest("tr").remove();
      showDeleteSuccessMessage();
    })
    .catch((error) => {
      showDeleteErrorMessage(error.response.data.message);
    });
}

function showDeleteSuccessMessage() {
  Swal.fire({
    title: "Deleted!",
    text: "The item has been deleted.",
    icon: "success",
  });
}

function showDeleteErrorMessage(message) {
  Swal.fire({
    title: "Error",
    text: message,
    icon: "error",
  });
}
