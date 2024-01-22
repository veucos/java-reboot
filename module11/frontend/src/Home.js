import React, { Component } from 'react';
import './App.css';
import { Link } from 'react-router-dom';
import { ButtonGroup, Container, Table, Button } from 'react-bootstrap';

class Home extends Component {

    constructor(props) {
        super(props);
        this.state = {users: []};
        this.remove = this.remove.bind(this);
    }

    componentDidMount() {
        fetch('/users')
            .then(response => response.json())
            .then(data => this.setState({users: data}));
    }

    async remove(id) {
        await fetch(`/users/${id}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(() => {
            let updatedUsers = [...this.state.users].filter(i => i.id !== id);
            this.setState({users: updatedUsers});
        });
    }

    render() {
        const {users} = this.state;

        const userList = users.map(user => {
            return <tr key={user.id}>
                <td style={{whiteSpace: 'nowrap'}}>{user.id}</td>
                <td>{user.name}</td>
                <td>{user.age}</td>
                <td>
                    <ButtonGroup>
                        <Link to={"/users/" + user.id}>
                            <Button size="sm" variant="primary">Редактировать</Button>
                        </Link>
                        <Button size="sm" variant="danger" onClick={() => this.remove(user.id)}>Удалить</Button>
                    </ButtonGroup>
                </td>
            </tr>
        });

        return (
            <div>
                <Container fluid>
                    <div className="float-right">
                        <Link to="/users/new">
                            <Button variant="success">Добавить пользователя</Button>
                        </Link>
                    </div>
                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th width="15%">Id</th>
                            <th width="30%">Имя</th>
                            <th width="15%">Возраст</th>
                            <th width="40%">Действия</th>
                        </tr>
                        </thead>
                        <tbody>
                        {userList}
                        </tbody>
                    </Table>
                </Container>
            </div>
        );
    }
}

export default Home;