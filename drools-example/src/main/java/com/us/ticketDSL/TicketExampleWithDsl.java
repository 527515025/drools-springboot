package com.us.ticketDSL;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
/**
 * @author  yyb
 */
public class TicketExampleWithDsl {
    public static void main(final String[] args) {
        KieContainer kc = KieServices.Factory.get().getKieClasspathContainer();
        execute( kc );
    }

    public static void execute( KieContainer kc ) {
        KieSession ksession = kc.newKieSession( "TicketWithDSLKS" );

        final Customer a = new Customer( "刘德华",
                "Gold" );
        final Customer b = new Customer( "郭富城",
                "Platinum" );
        final Customer c = new Customer( "张学友",
                "Silver" );
        final Customer d = new Customer( "黎明",
                "Silver" );

        final Ticket t1 = new Ticket( a );
        final Ticket t2 = new Ticket( b );
        final Ticket t3 = new Ticket( c );
        final Ticket t4 = new Ticket( d );

        ksession.insert( a );
        ksession.insert( b );
        ksession.insert( c );
        ksession.insert( d );

        ksession.insert( t1 );
        ksession.insert( t2 );
        ksession.insert( t3 );
        ksession.insert( t4 );
//        final FactHandle ft3 = ksession.insert( t3 );
        ksession.fireAllRules();

//        t3.setStatus( "Done" );

//        ksession.update( ft3,t3 );

        try {
            System.err.println( "[[ Sleeping 5 seconds ]]" );
            Thread.sleep( 5000 );
        } catch ( final InterruptedException e ) {
            e.printStackTrace();
        }

        System.err.println( "[[ awake ]]" );

        ksession.fireAllRules();

        ksession.dispose();
    }
}