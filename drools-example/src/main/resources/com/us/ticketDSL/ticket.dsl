

[condition][]我要买张票 "{status}"=customer : Customer( )   ticket : Ticket( customer == customer, status == "{status}" )

[condition][]这是一个 "{subscription}" 卡客户 "{status}"=customer : Customer(subscription == "{subscription}") ticket : Ticket( customer == customer, status == "{status}")

[consequence][]打印 "{message}"=System.out.println(customer.getName()+"{message} ");

[consequence][]优惠=ticket.setStatus("获得了一张代金劵");update(ticket);

[consequence][]Send  email=sendEscalationEmail( customer, ticket );
