package com.elimarafonso.IntranetFlix.Entity;

public enum DescricaoVideo {

	TITULO {
		@Override
		public boolean validaCampo(String titulo) {
			if (titulo.length() > 30 || titulo.isBlank()) {
				return true;
			}
			return false;
		}
	},
	DESCRICAO {
		@Override
		public boolean validaCampo(String descricao) {
			if (descricao.length() > 250 || descricao.isBlank()  ) {
				return true;
			}
			return false;
		}
	},
	URL {
		@Override
		public boolean validaCampo(String url) {
			if (url.length() > 250 || url.isBlank() ) {
				return true;
			}
			return false;
		}
	};

	public abstract boolean validaCampo(String string);

}
