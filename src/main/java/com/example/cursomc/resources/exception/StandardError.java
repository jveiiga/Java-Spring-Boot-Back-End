package com.example.cursomc.resources.exception;

import java.io.Serializable;

// Erros padrões
public class StandardError implements Serializable {

    private static final long serialVersionUID = 1L;

	private Long timeStamp;
	private Integer status;
	private String error;
	private String msg;
	// private String path;    

    public StandardError(Integer status, String msg, Long timeStamp) {
        this.status = status;
        this.msg = msg;
        this.timeStamp = timeStamp;
		// this.path = path;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

    // public String getPath() {
	// 	return path;
	// }

	// public void setPath(String path) {
	// 	this.path = path;
	// }
}
