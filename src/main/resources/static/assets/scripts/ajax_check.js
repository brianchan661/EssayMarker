function checkemail()
{
 var email=document.getElementById( "email" ).value;
	
 if(email)
 {
  $.ajax({
  type: 'post',
  url: 'checkdata.php',
  data: {
   user_email:email,
  },
  success: function (response) {
   $( '#email_status' ).html(response);
   if(response=="OK")	
   {
    return true;	
   }
   else
   {
    return false;	
   }
  }
  });
 }
 else
 {
  $( '#email_status' ).html("");
  return false;
 }
}

function checkall()
{
 var emailhtml=document.getElementById("email_status").innerHTML;

 if(emailhtml=="")
 {
  return true;
 }
 else
 {
  return false;
 }
}
