package com.booking.theatre.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.sun.istack.NotNull;

public class PatchDocument {

	public enum OpEnum {
	    ADD("add"),
	    
	    REMOVE("remove"),
	    
	    REPLACE("replace"),
	  
	    TEST("test");

	    private String value;

	    OpEnum(String value) {
	      this.value = value;
	    }
	    
	    @Override
	    @JsonValue
	    public String toString() {
	      return String.valueOf(value);
	    }

	    @JsonCreator
	    public static OpEnum fromValue(String text) {
	      for (OpEnum b : OpEnum.values()) {
	        if (String.valueOf(b.value).equals(text)) {
	          return b;
	        }
	      }
	      return null;
	    }
	}
	
	@JsonProperty("op")
	  private OpEnum op = null;

	  @JsonProperty("path")
	  private String path = null;

	  @JsonProperty("value")
	  private Object value = null;

	  @JsonProperty("from")
	  private String from = null;

	  public PatchDocument op(OpEnum op) {
	    this.op = op;
	    return this;
	  }
	  @NotNull
	  public OpEnum getOp() {
	    return op;
	  }

	  public void setOp(OpEnum op) {
	    this.op = op;
	  }

	  public PatchDocument path(String path) {
	    this.path = path;
	    return this;
	  }
	  
	  @NotNull
	  public String getPath() {
	    return path;
	  }

	  public void setPath(String path) {
	    this.path = path;
	  }

	  public PatchDocument value(Object value) {
	    this.value = value;
	    return this;
	  }

	   /**
	   * The value to be used within the operations.
	   * @return value
	  **/
	  public Object getValue() {
	    return value;
	  }

	  public void setValue(Object value) {
	    this.value = value;
	  }

	  public PatchDocument from(String from) {
	    this.from = from;
	    return this;
	  }

	   /**
	   * A string JSON-Pointer describing the source for some operations.
	   * @return from
	  **/
	  public String getFrom() {
	    return from;
	  }

	  public void setFrom(String from) {
	    this.from = from;
	  }


	  @Override
	  public boolean equals(java.lang.Object o) {
	    if (this == o) {
	      return true;
	    }
	    if (o == null || getClass() != o.getClass()) {
	      return false;
	    }
	    PatchDocument jsonPatchOperation = (PatchDocument) o;
	    return Objects.equals(this.op, jsonPatchOperation.op) &&
	        Objects.equals(this.path, jsonPatchOperation.path) &&
	        Objects.equals(this.value, jsonPatchOperation.value) &&
	        Objects.equals(this.from, jsonPatchOperation.from);
	  }

	  @Override
	  public int hashCode() {
	    return Objects.hash(op, path, value, from);
	  }

	  @Override
	  public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("class patchOperation {\n");
	    
	    sb.append("    op: ").append(toIndentedString(op)).append("\n");
	    sb.append("    path: ").append(toIndentedString(path)).append("\n");
	    sb.append("    value: ").append(toIndentedString(value)).append("\n");
	    sb.append("    from: ").append(toIndentedString(from)).append("\n");
	    sb.append("}");
	    return sb.toString();
	  }
	  
	  private String toIndentedString(java.lang.Object o) {
		    if (o == null) {
		      return "null";
		    }
		    return o.toString().replace("\n", "\n    ");
		  }

}
