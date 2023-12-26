import React from "react";

function Account(props) {
    return (
      <div>
        <h1>회원가입</h1>
        <form onSubmit={props.handleSubmit}>
          <input type="text" name='name' value={props.id} onChange={props.handleChange}/>
          <input type="password" name='password' value={props.pw} onChange={props.handleChange}/>
          <input type="submit" value="회원가입"/>
        </form>
      </div>
    )

}

export default Account;