package br.com.mfs.casamento.interceptadores;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

@Interceptor
@Transactional
public class TransactionalInterceptador implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	
	private @Inject @BancoDadosMysql EntityManager manager;

	@AroundInvoke
	public Object invoke(InvocationContext context) throws Exception {
		EntityTransaction trx = manager.getTransaction();
		boolean criador = false;
		try {
			
			if (!trx.isActive()) {
//				System.out.println("Transação não estar ativa");
//				trx.begin();
//				System.out.println("Transação aberta");
//				trx.rollback();
//				System.out.println("Rollback");
//				trx.begin();
//				System.out.println("Transação aberta");
//				criador = true;
				trx.begin();
				criador = true;
			} else {
				trx.begin();
			}
			
			Object resultado = context.proceed();
			trx.commit();
			return resultado;
		} catch (Exception e) {
			System.out.println("Deu pau");
			if (trx != null && criador) {
				trx.rollback();
			}
			throw e;
		} finally {
			if (trx != null && trx.isActive() && criador) {
				System.out.println("Transacao nula, ativa ou criador true");
				trx.commit();
			}
		}
	}
}