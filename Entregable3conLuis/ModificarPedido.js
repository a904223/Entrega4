
    function edit_row() {
        var filas = document.getElementById("data_table").rows.length;
        
       var i;
        var contador = document.getElementById("contador");
        contador.innerHTML="<input type=\"hidden\" name=\"cont\" value=\"" + filas + "\"></input>";

        
        for (i=1; i < (filas); i++){
            document.getElementById("modificar").style.display="none";
            document.getElementById("guardar").style.display="block";
            
            var customerID=document.getElementById((i) + "_1");
           
            var customer=document.getElementById((i) + "_2");
            var product=document.getElementById((i) + "_3");
            var price=document.getElementById((i) + "_4");
            var date=document.getElementById((i) + "_5");
            
            

            var customerID_data=customerID.innerHTML;
            var customer_data=customer.innerHTML;
            var product_data=product.innerHTML;
            var price_data=price.innerHTML;
            var date_data=date.innerHTML;
            
            

            customerID.innerHTML="<input type='text' name='customerID" + i + "' size=\"10\" id=\"" + i + "-1\" value=\"" + customerID_data + "\"></input>";
            
            customer.innerHTML="<input type='text' name=\"customer\" size=\"10\" id=\"" + i + "-2\" value=\"" + customer_data + "\"></input>";
            product.innerHTML="<input type='text' name=\"product\" size=\"10\" id=\"" + i + "-3\" value=\"" + product_data + "\"></input>";
            price.innerHTML="<input type='text' name=\"price\" size=\"10\" id=\"" + i + "-4\" value=\"" + price_data + "\"></input>";
            date.innerHTML="<input type='text' name=\"date\" size=\"10\" id=\"" + i + "-5\" value=\"" + date_data + "\"></input>";
            
            
        }


    }

    function save_row() {
        var filas = document.getElementById("data_table").rows.length;
        var j;
        var customerID_array = [];
        var customer_array = [];
        var product_array = [];
        var price_array = [];
        var date_array = [];
        
        
        for (j=1; j < (filas); j++){
            
            var customerID_val=document.getElementById( j + '-1').value;
            var customer_val=document.getElementById( j + '-2').value;
            var product_val=document.getElementById( j + '-3').value;
            var price_val=document.getElementById( j + '-4').value;
            var date_val=document.getElementById( j + '-5').value;
            

            document.getElementById(j + "_1").innerHTML=customerID_val;
            document.getElementById(j + "_2").innerHTML=customer_val;
            document.getElementById(j + "_3").innerHTML=product_val;
            document.getElementById(j + "_4").innerHTML=price_val;
            document.getElementById(j + "_5").innerHTML=date_val;
            
            
            customerID_array[j] = document.getElementById(j + "_1").innerHTML;
            customer_array[j] = document.getElementById(j + "_2").innerHTML;
            product_array[j] = document.getElementById(j + "_3").innerHTML;
            price_array[j] = document.getElementById(j + "_4").innerHTML;
            date_array[j] = document.getElementById(j + "_5").innerHTML;
            

            document.getElementById("modificar").style.display="block";
            document.getElementById("guardar").style.display="none";
        }

        var stringify1 = JSON.stringify(customerID_array);
        var stringify2 = JSON.stringify(customer_array);
        var stringify3 = JSON.stringify(product_array);
        var stringify4 = JSON.stringify(price_array);
        var stringify5 = JSON.stringify(date_array);
            $.ajax({
                type: "post",
                url: "GuardarModificacion",
                data: {customerID: stringify1, customer: stringify2,product:stringify3,price:stringify4,date:stringify5, filas: filas},
                success: function(){
                    alert("Success, con j= " + j);
                }
            });
        
        
                
                $("#mensaje").text("The data has been modified correctly");
                function hideMsg(){
                    $("#mensaje").fadeOut();
                }
                setTimeout(hideMsg,4000);
        
     }

    