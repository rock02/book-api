package br.com.alelo.utils;

import lombok.NoArgsConstructor;

public class FileKey {

	public static FileKey.FileKeyBuilder builder() {
        return new FileKey.FileKeyBuilder();
    }

    @NoArgsConstructor
    public static class FileKeyBuilder {
        private String fileExtension;
        private String fileName;

        public FileKey.FileKeyBuilder withFileExtension( String fileExtension ) {
            this.fileExtension = fileExtension;
            return this;
        }

        public FileKey.FileKeyBuilder withFileName( String fileName ) {
            this.fileName = fileName;
            return this;
        }

        public String build() {
            return new StringBuilder()
                    .append( fileName )
                    .append( "." )
                    .append( fileExtension )
                    .toString();
        }
    }
}
