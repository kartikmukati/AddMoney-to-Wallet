<%@page import="java.sql.ResultSet"%>
<%@page import="db.DBConnector"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Expense</title>
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
                            <h5>Recharge</h5>
                        </div>
                        <div class="card-body ">
                            <div class="form-group">
                                <label for="exampleInputEmail1">Amount</label>
                                <input type="text" class="form-control mx-auto" id="amount" name="amount" style="width:20rem" required>
                            </div>
                            <button type="submit" id="btn" class="btn btn-primary">Pay</button>
                        </div>
                        <a href="index.jsp" class="btn btn-primary"> Add Money </a>
                    </div>
                </div>
            </div>
        </div>
        <%
            ResultSet rs = DBConnector.getTransaction();
        %>
        <div class="container">
            <div class="row">
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th scope="col">Order Id</th>
                            <th scope="col">Available Balance</th>
                            <th scope="col">Amount</th>
                            <th scope="col">Credit/Debit</th>
                            <th scope="col">Date/Time</th>
                        </tr>
                    </thead>
                    <%
                        while (rs.next()) {
                            String s = rs.getString(4);
                            if (s.equals("Credit")) {
                    %>
                    <tbody>
                        <tr class="bg-success text-white">
                            <td><%= rs.getInt(1)%></td>
                            <td><%= rs.getInt(2)%></td>
                            <td><%= rs.getInt(3)%></td>
                            <td><%= rs.getString(4)%></td>
                            <td><%= rs.getTimestamp(5)%></td>
                        </tr>
                    </tbody>
                    <% } else if (s.equals("Debit")) {

                    %>
                    <tbody>
                        <tr class="bg-danger text-white">
                            <td><%= rs.getInt(1)%></td>
                            <td><%= rs.getInt(2)%></td>
                            <td><%= rs.getInt(3)%></td>
                            <td><%= rs.getString(4)%></td>
                            <td><%= rs.getTimestamp(5)%></td>
                        </tr>
                    </tbody>
                    <%
                            }
                        }
                    %>
                </table>
            </div>
        </div>
        <script>
            $(document).ready(function() {
                $('#btn').click(function() {

                    var balance = Number(document.getElementById('balance').innerHTML)

                    //var balance = 0
                    var amountString = $('#amount').val()
                    var amount = Number(amountString)
                    if (amountString.trim().length <= 0 || isNaN(amount)) {
                        alert('Enter some amount')
                    }
                    else if (amount > balance) {
                        alert('Insufficient balance')
                    }
                    else {
                        $.ajax({
                            method: 'POST',
                            url: 'DebitMoney',
                            data: {
                                balance: balance,
                                amount: amount
                            },
                            success: function(data) {
                                $('#balance').text(data)
                                // window.location = 'index.jsp'
                                window.location.reload()
                            }
                        })
                    }
                })
            })
        </script>
    </body>
</html>
