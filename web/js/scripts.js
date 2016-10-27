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

function confirmar(){
   bootbox.dialog({
  title: 'My title',  
  onEscape: true,       // Handles Escape
  buttons: {
    cancel: {
      label: 'Cancel',
      className: 'btn-default',
      callback: function () {
      }
    },
    confirm: {
      label: 'OK',
      className: 'btn-primary',
      callback: onSubmit  // Handles OK button
    }
  }
});
    };

function confirmar2(){
    $('#modal-dialog').on('show', function() {
    var link = $(this).data('link'),
        confirmBtn = $(this).find('.confirm');
})

$('#btnOk').click(function() {
  
    // handle form processing here
  	
  	alert('submit form');
        $('form').submit();
  
});
   }