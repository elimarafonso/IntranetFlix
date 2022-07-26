package com.elimarafonso.IntranetFlix.Entity;

public enum DescricaoCategoria {

	TITULO {
		@Override
		public Boolean tamanhoDoCampo(String titulo) {

			if (titulo == null || titulo.isBlank() || titulo.length() > 30) {
				return true;
			}

			return false;
		}
	},
	COR {
		@Override
		public Boolean tamanhoDoCampo(String cor) {

			if (cor == null || cor.isBlank() || cor.length() > 30) {

				return true;
			}

			return false;
		}
	};

	public abstract Boolean tamanhoDoCampo(String valor);
}
