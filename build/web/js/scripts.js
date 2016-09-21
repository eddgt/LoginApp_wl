//funcion para ventana modal
function initDialogNAF() {
 $("#idOfDialogPlaceHolder").dialog({
     modal: true,
     buttons: {
            Guardar: function () {
                $("#okButton").click();
            },
            Cancelar: function () {
                $(this).dialog("close");
            }
     }
 });
}