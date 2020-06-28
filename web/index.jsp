<%@page import="db.DBConnector"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Money</title>
        <link rel="stylesheet" href="Public/css/bootstrap.min.css" />
        <script type="text/javascript" src="./Public/js/jqueryCdn.js"></script>
        <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    </head>
    <body>
         <div class="container">
            <div class="row">
                <div class="col-2"></div>
                <div class="col-8">
                    <div class="card text-white bg-primary mt-3 mb-3 mx-auto text-center" style="width: 30rem;">
                        <div class="card-header">
                            <%
                                int balance = DBConnector.getAvailableBalance();
                            %>
                            <div class="row">
                                <div class="col-8">
                                    <h5> Available Balance: </h5>
                                </div>
                                <div class="col-3">
                                    <h5 id="balance" class="float-right"><%=balance%></h5>
                                </div>
                                <div class="col-1">
                                    <h5 class="float-left">$</h5>
                                </div>
                            </div>
                           
                        </div>
                    </div>
                </div>
                <div class="col-2"></div>
            </div>
             <div class="row">
                 <div class="col">
                      <div class="card text-center mt-2 shadow p-3 mb-5 bg-white rounded">
                            <div class="card-header">
                                <h5>Add Money</h5>
                            </div>
                            <div class="card-body ">
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Amount</label>
                                    <input type="text" class="form-control mx-auto" id="amount" name="amount" style="width:20rem" required>
                                </div>
                            <button type="submit" id="btn" class="btn btn-primary">Submit</button>
                           </div>
                        </div>
                 </div>
             </div>
         </div>
         <script>
             $(document).ready(function(){
                 $('#btn').click(function(){
                    var balance = document.getElementById('balance').innerHTML
                    //var balance = 0
                    var amount = $('#amount').val() 

                    if(amount.trim().length <= 0 || isNaN(amount)){
                        alert('Enter some amount')
                    }
                    else{
                        $.ajax({
                            method: 'POST',
                            url:'AddMoney',
                            data:{
                                balance: balance,
                                amount: amount
                            },
                            success:function(data){
                                $('#balance').text(data) 
                                window.location = 'expense.jsp'
                            }
                        })
                    }
                })
            })
         </script>
    </body>
</html>
